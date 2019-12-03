/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.reactnativecommunity.viewpager;

import java.util.Map;

import android.view.View;

import androidx.annotation.NonNull;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.reactnativecommunity.viewpager.androidx.ReactViewPager2;

import javax.annotation.Nullable;

public class ReactViewPagerManager extends ViewGroupManager<ReactViewPager2> {

  private static final String REACT_CLASS = "RNCViewPager";

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  @NonNull
  @Override
  protected ReactViewPager2 createViewInstance(@NonNull ThemedReactContext reactContext) {
    return new ReactViewPager2(reactContext);

  }
}
