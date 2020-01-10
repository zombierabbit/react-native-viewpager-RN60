/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.reactnativecommunity.viewpager;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2;

import java.util.Map;

import static com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2.ORIENTATION_HORIZONTAL;
import static com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING;
import static com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2.SCROLL_STATE_IDLE;
import static com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING;


public class ReactViewPagerManager extends ViewGroupManager<ViewPager2> {

    private static final String REACT_CLASS = "RNCViewPager";
    private static final int COMMAND_SET_PAGE = 1;
    private static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;
    private EventDispatcher eventDispatcher;

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected ViewPager2 createViewInstance(@NonNull ThemedReactContext reactContext) {

        final ViewPager2 vp = new ViewPager2(reactContext);
        FragmentAdapter adapter = new FragmentAdapter((FragmentActivity) reactContext.getCurrentActivity());
        vp.setAdapter(adapter);
        vp.setOrientation(ORIENTATION_HORIZONTAL);
        eventDispatcher = reactContext.getNativeModule(UIManagerModule.class).getEventDispatcher();
        return vp;
    }

    @Override
    public void addView(ViewPager2 parent, View child, int index) {
        if (child == null) {
            return;
        }
        ((FragmentAdapter) parent.getAdapter()).addFragment(child, index);
    }

    @Override
    public int getChildCount(ViewPager2 parent) {
        return parent.getAdapter().getItemCount();
    }


    @Override
    public View getChildAt(ViewPager2 parent, int index) {
        return ((FragmentAdapter) parent.getAdapter()).getChildAt(index);
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }


    @ReactProp(name = "scrollEnabled", defaultBoolean = true)
    public void setScrollEnabled(ViewPager2 viewPager, boolean value) {
        viewPager.setUserInputEnabled(value);
    }

    @ReactProp(name = "orientation")
    public void setOrientation(ViewPager2 viewPager, String value) {
        viewPager.setOrientation(value.equals("horizontal") ? ViewPager2.ORIENTATION_HORIZONTAL : ViewPager2.ORIENTATION_VERTICAL);
    }


    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(
                PageScrollEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScroll"),
                PageScrollStateChangedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageScrollStateChanged"),
                PageSelectedEvent.EVENT_NAME, MapBuilder.of("registrationName", "onPageSelected"));
    }


    @Override
    public Map<String, Integer> getCommandsMap() {
        return MapBuilder.of(
                "setPage",
                COMMAND_SET_PAGE,
                "setPageWithoutAnimation",
                COMMAND_SET_PAGE_WITHOUT_ANIMATION);
    }

    @Override
    public void receiveCommand(@NonNull final ViewPager2 root, int commandId, @Nullable final ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        Assertions.assertNotNull(root);
        Assertions.assertNotNull(args);
        switch (commandId) {
            case COMMAND_SET_PAGE: {
                root.setCurrentItem(args.getInt(0), true);
                return;

            }
            case COMMAND_SET_PAGE_WITHOUT_ANIMATION: {
                // there is a bug here
                root.setCurrentItem(args.getInt(0), false);
                eventDispatcher.dispatchEvent(new PageSelectedEvent(root.getId(), args.getInt(0)));
                return;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandId,
                        getClass().getSimpleName()));
        }
    }


    @ReactProp(name = "pageMargin", defaultFloat = 0)
    public void setPageMargin(ViewPager2 pager, float margin) {
        int pageMargin = (int) PixelUtil.toPixelFromDIP(margin);
        pager.setPadding(pageMargin, pageMargin, pageMargin, pageMargin);
    }

}
