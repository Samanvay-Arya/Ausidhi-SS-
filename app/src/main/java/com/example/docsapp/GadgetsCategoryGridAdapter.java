package com.example.docsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GadgetsCategoryGridAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> GadgetsCategoryNameList;
    ArrayList<Integer> GadgetsCategoryImageList;

    public GadgetsCategoryGridAdapter(Context context, ArrayList<String> GadgetsCategoryNameList, ArrayList<Integer> GadgetsCategoryImageList) {
        this.context = context;
        this.GadgetsCategoryNameList = GadgetsCategoryNameList;
        this.GadgetsCategoryImageList = GadgetsCategoryImageList;
    }

    @Override
    public int getCount() {
        return GadgetsCategoryImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        @SuppressLint("ViewHolder") View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gadgets_category_card_design,parent,false);
        ImageView GadgetsCategoryImage=view.findViewById(R.id.GadgetsCategoryImage);
        TextView GadgetsCategoryName=view.findViewById(R.id.GadgetsCategoryName);
        GadgetsCategoryImage.setImageResource(GadgetsCategoryImageList.get(position));
        GadgetsCategoryName.setText(GadgetsCategoryNameList.get(position));
        return view;
    }
}
