package com.example.valiev_ra.testapp.Main.Fragment.Stations.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.valiev_ra.testapp.R;

public class ViewHolderItem extends RecyclerView.ViewHolder {
    private TextView name;

    ViewHolderItem(View v) {
        super(v);
        name = v.findViewById(R.id.tv_name);
    }

    public TextView getName() {
        return name;
    }
}