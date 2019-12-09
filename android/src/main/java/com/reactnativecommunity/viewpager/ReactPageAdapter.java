package com.reactnativecommunity.viewpager;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReactPageAdapter extends RecyclerView.Adapter<ReactPageAdapter.ReactAdapterViewHolder> {

    private ArrayList<View> children = new ArrayList<>();

    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

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
        Log.d("ReactPageAdapter", "onBindViewHolder: " + position);
        holder.bind(position);
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
    public long getItemId(int position) {
        if (children.get(position) != null) {
            return children.get(position).hashCode();
        }
        return super.getItemId(position);
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

        public void bind(int position) {
            View reactNativeView = children.get(position);
            LinearLayout rootview = (LinearLayout) itemView;
            View asd = new View(reactNativeView.getContext());
            asd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            asd.setBackgroundColor(Color.parseColor(mColors[position]));

            Log.d("ReactPageAdapter", "bind: " + rootview.getChildCount());
            if(rootview.getChildCount() > 0){
                rootview.removeViewAt(position);
            }
            // THIS DOES NOT
//            rootview.addView(asd);
            //THIS WORKS
            rootview.setBackgroundColor(Color.parseColor(mColors[position]));


//            Log.d("ReactPageAdapter", "bind: " + rootview.toString());
//            Log.d("ReactPageAdapter", "bind: " + reactNativeView.toString());
//            if (reactNativeView.getParent() != null) {
//                Log.d("ReactPageAdapter", "bind: " + reactNativeView.toString());
////                if (rootview.hashCode() != reactNativeView.getParent().hashCode()) {
////                    rootview.addView(reactNativeView);
////                }
//            } else {
//                rootview.addView(reactNativeView);
//            }
        }

    }
}
