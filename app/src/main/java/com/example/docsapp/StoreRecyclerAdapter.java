package com.example.docsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StoreRecyclerAdapter extends RecyclerView.Adapter<StoreRecyclerAdapter.StoreViewHolder> {

    public ArrayList<String> StoreNameList;
    public ArrayList<String> StoreRatingList;
    public ArrayList<Integer> StoreImageList;
    public Context context;

    public StoreRecyclerAdapter(ArrayList<String> storeNameList, ArrayList<String> storeRatingList, ArrayList<Integer> storeImageList,Context context) {
        this.StoreNameList = storeNameList;
        this.StoreRatingList = storeRatingList;
        this.StoreImageList = storeImageList;
        this.context=context;
    }

    @NonNull
    @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.store_card_design,parent,false);
        return new StoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        holder.imageViewStoreImage.setImageResource(StoreImageList.get(position));
        holder.textViewStoreName.setText(StoreNameList.get(position));
        holder.textViewStoreRatings.setText(StoreRatingList.get(position));
    }

    @Override
    public int getItemCount() {
        return StoreImageList.size();
    }

    public static class StoreViewHolder extends RecyclerView.ViewHolder{
        TextView textViewStoreName, textViewStoreRatings;
        ImageView imageViewStoreImage;
        public StoreViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewStoreName=itemView.findViewById(R.id.Store_Name);
            textViewStoreRatings=itemView.findViewById(R.id.Store_ratings);
            imageViewStoreImage=itemView.findViewById(R.id.store_image_view);
        }
    }
}
