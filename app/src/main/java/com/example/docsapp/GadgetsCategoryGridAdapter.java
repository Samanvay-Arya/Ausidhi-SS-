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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GadgetsCategoryGridAdapter extends FirebaseRecyclerAdapter<GadgetsCategoryModel,GadgetsCategoryGridAdapter.viewHolder> {


    private GadgetsCategoryGridAdapter.OnItemClickListener listener;
    public GadgetsCategoryGridAdapter(@NonNull FirebaseRecyclerOptions<GadgetsCategoryModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull GadgetsCategoryModel model) {
        holder.Name.setText(model.getName());
        Glide.with(holder.Image.getContext()).load(model.getImage()).into(holder.Image);

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gadgets_category_card_design, parent, false);
        return new viewHolder(view);
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        ImageView Image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.GadgetsCategoryName);
            Image=itemView.findViewById(R.id.GadgetsCategoryImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position!=RecyclerView.NO_POSITION && listener!=null){
                        listener.onItemClick(getSnapshots().getSnapshot(position),position);

                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemClick(DataSnapshot dataSnapshot, int position);
    }
    public void setOnItemCLickListener(GadgetsCategoryGridAdapter.OnItemClickListener listener){
        this.listener= listener;

    }
}
