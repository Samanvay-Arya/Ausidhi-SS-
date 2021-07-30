package com.example.docsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends RecyclerView.Adapter<OrderRecyclerAdapter.viewHolder> {


    ArrayList<OrdersModel> DataList;

    public OrderRecyclerAdapter(ArrayList<OrdersModel> dataList) {
        DataList = dataList;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card_design,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.Name.setText(DataList.get(position).getName());
        holder.Price.setText(DataList.get(position).getPrice());
        holder.QTY.setText(DataList.get(position).getQTY());
        Glide.with(holder.Image.getContext()).load(DataList.get(position).getImage()).into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return DataList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView Name,Price, QTY;
        ImageView Image;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            Name=itemView.findViewById(R.id.Order_Product_Name_orders);
            Price=itemView.findViewById(R.id.Order_Product_Price_orders);
            QTY=itemView.findViewById(R.id.Order_Product_quantity_orders);
            Image=itemView.findViewById(R.id.Order_Image_View);
        }
    }
}
