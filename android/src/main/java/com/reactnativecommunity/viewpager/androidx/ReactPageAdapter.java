package com.reactnativecommunity.viewpager.androidx;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReactPageAdapter extends RecyclerView.Adapter<ReactPageAdapter.ReactAdapterViewHolder> {

    @NonNull
    @Override
    public ReactAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ReactAdapterViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ReactAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ReactAdapterViewHolder extends RecyclerView.ViewHolder{

//        public ReactAdapterViewHolder(@NonNull ViewGroup parent) {
//            super();
//
//        }
        public ReactAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
