package com.reactnativecommunity.viewpager;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import com.reactnativecommunity.viewpager.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FragmentAdapter extends FragmentStateAdapter {

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public FragmentAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }

    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    private ArrayList<ViewPagerFragment> children = new ArrayList<>();

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return children.get(position);
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public void addFragment(View child, int index) {
        children.add(new ViewPagerFragment());
        notifyDataSetChanged();
    }

    public View getChildAt(int index) {
        return children.get(index).getView();
    }
}
