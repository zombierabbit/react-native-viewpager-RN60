package com.reactnativecommunity.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.viewpager2.widget.ViewPager2;

public class ReactViewPagerWrapper extends LinearLayout {

    private ViewPager2 viewPager;
    private ReactPageAdapter pageAdapter;

    public ReactViewPagerWrapper(ViewPager2 viewPager, Context context) {
        super(context);
        this.viewPager = viewPager;
        this.pageAdapter = new ReactPageAdapter();
        this.addView(this.viewPager, 0);
        this.viewPager.setAdapter(pageAdapter);
    }

    public ReactViewPagerWrapper(Context context) {
        super(context);
    }

    public ReactViewPagerWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ReactViewPagerWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public ViewPager2 getViewPager() {
        return viewPager;
    }

    public ReactPageAdapter getPageAdapter() {
        return pageAdapter;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        ViewPager2 vp = null;
        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i) instanceof ViewPager2) {
                vp = (ViewPager2) getChildAt(i);
            }
        }
        if (vp != null) {
            vp.layout(left, top, right, bottom);
        }

    }
}
