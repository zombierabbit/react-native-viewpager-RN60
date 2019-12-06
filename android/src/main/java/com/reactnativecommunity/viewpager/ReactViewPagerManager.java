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


import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnativecommunity.viewpager.viewpager2.widget.ViewPager2;

import java.util.Map;


public class ReactViewPagerManager extends ViewGroupManager<ViewPager2> {

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
    protected ViewPager2 createViewInstance(@NonNull ThemedReactContext reactContext) {
        ViewPager2 vp = new ViewPager2(reactContext);
        vp.setBackgroundColor(Color.parseColor("#328ce6"));
        vp.setAdapter(new ReactPageAdapter());
        return vp;
    }

    @Override
    public void addView(ViewPager2 parent, View child, int index) {
        if(child == null) {
            return;
        }
        int hc = child.hashCode();
        Log.d("ReactPageAdapter", "addView: " + hc);
        Log.d("ReactPageAdapter", "addView: " + child.getId());
        ((ReactPageAdapter)parent.getAdapter()).addChild(child,index);
    }

    @Override
    public int getChildCount(ViewPager2 parent) {
        return parent.getAdapter().getItemCount();
    }


    @Override
    public View getChildAt(ViewPager2 parent, int index) {
        return ((ReactPageAdapter)parent.getAdapter()).getChildAt(index);
    }

    @Override
    public boolean needsCustomLayoutForChildren() {
        return true;
    }


    @ReactProp(name = "scrollEnabled", defaultBoolean = true)
    public void setScrollEnabled(ViewPager2 viewPager, boolean value) {
        //TODO Implement
    }

    @ReactProp(name = "orientation")
    public void setOrientation(ViewPager2 viewPager, String value) {
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

//    @Override
//    public void receiveCommand(@NonNull ReactViewPagerWrapper root, String commandId, @Nullable ReadableArray args) {
//        super.receiveCommand(root, commandId, args);
//        Assertions.assertNotNull(args);
//        Log.d("", "receiveCommand: "+ commandId);
////        switch (commandId) {
////            case COMMAND_SET_PAGE: {
//////                viewPager.setCurrentItemFromJs(args.getInt(0), true);
////                return;
////
////            }
////            case COMMAND_SET_PAGE_WITHOUT_ANIMATION: {
//////                viewPager.setCurrentItemFromJs(args.getInt(0), false);
////                return;
////            }
////            default:
////                throw new IllegalArgumentException(String.format(
////                        "Unsupported command %d received by %s.",
////                        commandId,
////                        getClass().getSimpleName()));
////        }
//    }


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
    public void setPageMargin(ViewPager2 pager, float margin) {
        //TODO implement
    }

}
