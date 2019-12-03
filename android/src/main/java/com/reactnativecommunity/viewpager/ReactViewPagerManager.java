/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 * <p>
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.reactnativecommunity.viewpager;

import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import java.util.Map;


public class ReactViewPagerManager extends ViewGroupManager<ReactViewPagerWrapper> {

    private static final String REACT_CLASS = "RNCViewPager";
    private static final int COMMAND_SET_PAGE = 1;
    private static final int COMMAND_SET_PAGE_WITHOUT_ANIMATION = 2;

    @NonNull
    @Override
    public String getName() {
        return REACT_CLASS;
    }

    @NonNull
    @Override
    protected ReactViewPagerWrapper createViewInstance(@NonNull ThemedReactContext reactContext) {
        ReactViewPagerWrapper reactViewPagerWrapper = new ReactViewPagerWrapper(new ViewPager2(reactContext), reactContext);
        reactViewPagerWrapper.getViewPager().setBackgroundColor(Color.parseColor("#328ce6"));
        return reactViewPagerWrapper;
    }

    @Override
    public void addView(ReactViewPagerWrapper parent, View child, int index) {
        super.addView(parent, child, index);
        if(child == null) {
            return;
        }
        parent.getPageAdapter().addChild(child, index);
        parent.invalidate();
    }

    @Override
    public int getChildCount(ReactViewPagerWrapper parent) {
        return parent.getPageAdapter().getItemCount();
    }

    @Override
    public View getChildAt(ReactViewPagerWrapper parent, int index) {
        return parent.getPageAdapter().getChildAt(index);
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }


    @ReactProp(name = "scrollEnabled", defaultBoolean = true)
    public void setScrollEnabled(ReactViewPagerWrapper viewPager, boolean value) {
        //TODO Implement
    }

    @ReactProp(name = "orientation")
    public void setOrientation(ReactViewPagerWrapper viewPager, String value) {
        //TODO Implement
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
    public void receiveCommand(@NonNull ReactViewPagerWrapper root, String commandId, @Nullable ReadableArray args) {
        super.receiveCommand(root, commandId, args);
        Assertions.assertNotNull(args);
        Log.d("", "receiveCommand: "+ commandId);
//        switch (commandId) {
//            case COMMAND_SET_PAGE: {
////                viewPager.setCurrentItemFromJs(args.getInt(0), true);
//                return;
//
//            }
//            case COMMAND_SET_PAGE_WITHOUT_ANIMATION: {
////                viewPager.setCurrentItemFromJs(args.getInt(0), false);
//                return;
//            }
//            default:
//                throw new IllegalArgumentException(String.format(
//                        "Unsupported command %d received by %s.",
//                        commandId,
//                        getClass().getSimpleName()));
//        }
    }


    public void receiveCommandOld(
            ReactViewPager viewPager,
            int commandType,
            @Nullable ReadableArray args) {
        Assertions.assertNotNull(viewPager);
        Assertions.assertNotNull(args);
        switch (commandType) {
            case COMMAND_SET_PAGE: {
                viewPager.setCurrentItemFromJs(args.getInt(0), true);
                return;

            }
            case COMMAND_SET_PAGE_WITHOUT_ANIMATION: {
                viewPager.setCurrentItemFromJs(args.getInt(0), false);
                return;
            }
            default:
                throw new IllegalArgumentException(String.format(
                        "Unsupported command %d received by %s.",
                        commandType,
                        getClass().getSimpleName()));
        }
    }


    @ReactProp(name = "pageMargin", defaultFloat = 0)
    public void setPageMargin(ReactViewPagerWrapper pager, float margin) {
        //TODO implement
    }

}
