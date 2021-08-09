package com.example.docsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.Locale;

public class AllProductUniversalAdapter extends FirebaseRecyclerAdapter<AllProductUniversalModel,AllProductUniversalAdapter.viewHolder> {


    public AllProductUniversalAdapter(@NonNull FirebaseRecyclerOptions<AllProductUniversalModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull AllProductUniversalModel model) {
        holder.Name.setText(model.getName());
        holder.Price.setText(model.getPrice());
        holder.Discount.setText(model.getDiscount());
        holder.MRP.setText(model.getMRP());
        holder.Star.setText(model.getRate());
        Glide.with(holder.Image.getContext()).load(model.getImage()).into(holder.Image);
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gedgets_card_design, parent, false);
        return new viewHolder(view);
    }


    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView Name,Price,MRP,Star,Discount;
        ImageView Image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.AllProductUniversal_Product_Name);
            Price=itemView.findViewById(R.id.AllProductUniversal_Product_Price);
            MRP=itemView.findViewById(R.id.AllProductUniversal_Product_MRP);
            Star=itemView.findViewById(R.id.AllProductUniversal_Product_Star_Rating);
            Discount=itemView.findViewById(R.id.AllProductUniversal_Product_Discount);
            Image=itemView.findViewById(R.id.AllProductUniversal_Product_Image);

        }
    }
}
