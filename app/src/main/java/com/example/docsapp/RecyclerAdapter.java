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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CountryViewHolder> {
    public ArrayList<String> countryNameList;
    public ArrayList<String>detailsList;
    public ArrayList<Integer>imageList;
    public Context context;

    public RecyclerAdapter(ArrayList<String> countryNameList, ArrayList<String> detailsList, ArrayList<Integer> imageList, Context context) {
        this.countryNameList = countryNameList;
        this.detailsList = detailsList;
        this.imageList = imageList;
        this.context = context;
    }


    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_design,parent,false);

        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        holder.imageView.setImageResource(imageList.get(position));
        holder.textViewCountryName.setText(countryNameList.get(position));
        holder.textViewDetails.setText(detailsList.get(position));


    }

    @Override
    public int getItemCount() {
        return countryNameList.size();
    }


    public static class CountryViewHolder extends RecyclerView.ViewHolder{
        public TextView textViewCountryName, textViewDetails;
        public ImageView imageView;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCountryName=itemView.findViewById(R.id.textviewcountryname);
            textViewDetails=itemView.findViewById(R.id.textViewdetail);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }


}
