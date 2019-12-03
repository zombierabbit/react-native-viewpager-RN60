package com.reactnativecommunity.viewpager;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReactPageAdapter extends RecyclerView.Adapter<ReactPageAdapter.ReactAdapterViewHolder> {

    private ArrayList<View> children = new ArrayList<>();

    @NonNull
    @Override
    public ReactAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout rootView = new LinearLayout(parent.getContext());
        rootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        rootView.setOrientation(LinearLayout.HORIZONTAL);
        rootView.setBackgroundColor(Color.parseColor("#32a852"));
        return new ReactAdapterViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactAdapterViewHolder holder, int position) {
        holder.bind(children.get(position));
    }

    public void addChild(View children, int index) {
        this.children.add(index, children);
        notifyDataSetChanged();

    }

    public void setChildren(ArrayList<View> children) {
        this.children.clear();
        this.children = children;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return children.size();
    }

    public View getChildAt(int index) {
        return children.get(index);
    }

    class ReactAdapterViewHolder extends RecyclerView.ViewHolder {

        public ReactAdapterViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);
        }

        public void bind(View reactNativeView) {
            LinearLayout rootview = (LinearLayout) itemView;
            rootview.addView(reactNativeView);


        }
    }
}
