package com.example.docsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder>{
    public ArrayList<String> CategoryName;
    public ArrayList<String>CategoryDetail;
    public ArrayList<Integer>CategoryImage;
    public Context context;
    CardView cardView;

    public CategoryRecyclerAdapter(ArrayList<String> categoryName, ArrayList<String> categoryDetail, ArrayList<Integer> categoryImage, Context context) {
        this.CategoryName = categoryName;
        this.CategoryDetail = categoryDetail;
        this.CategoryImage = categoryImage;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_recycler_design,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.textViewCategoryName.setText(CategoryName.get(position));
        holder.textViewCategoryDetail.setText(CategoryDetail.get(position));
        holder.imageViewCategory.setImageResource(CategoryImage.get(position));
        holder.imageViewCategory.setAnimation(AnimationUtils.loadAnimation(context,R.anim.photo_anim));
        holder.cardView.setAnimation(AnimationUtils.loadAnimation(context,R.anim.card_animation));

    }

    @Override
    public int getItemCount() {
        return CategoryName.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewCategoryName,textViewCategoryDetail;
        public ImageView imageViewCategory;
        public CardView cardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCategoryName=itemView.findViewById(R.id.textViewCategoryName);
            textViewCategoryDetail=itemView.findViewById(R.id.textViewCategoryDetail);
            imageViewCategory=itemView.findViewById(R.id.imageViewCategory);
            cardView=itemView.findViewById(R.id.cardview);
        }
    }


}
