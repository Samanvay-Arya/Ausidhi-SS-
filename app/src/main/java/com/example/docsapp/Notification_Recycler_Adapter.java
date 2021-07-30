package com.example.docsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;

import java.util.ArrayList;

public class Notification_Recycler_Adapter extends FirebaseRecyclerAdapter<NotificationModel,Notification_Recycler_Adapter.textViewHolder>  {

    private OnItemClickListener listener;
    public Notification_Recycler_Adapter(@NonNull FirebaseRecyclerOptions<NotificationModel> options) {
        super(options);
    }

    @NonNull
    @Override
    public textViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View View= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card_design , parent,false);

        return new textViewHolder(View);
    }

    @Override
    protected void onBindViewHolder(@NonNull textViewHolder holder, int position, @NonNull NotificationModel model) {
        holder.NotificationHeading.setText(model.getTitle());
        holder.NotificationDetail.setText(model.getTime());
        Glide.with(holder.NotificationImageList.getContext()).load(model.getImage()).into(holder.NotificationImageList);
    }


    public class textViewHolder extends RecyclerView.ViewHolder {
       public TextView NotificationHeading, NotificationDetail;
        public ImageView NotificationImageList;

        public textViewHolder(@NonNull View itemView) {


            super(itemView);
            NotificationHeading =itemView.findViewById(R.id.Notification_Heading);
            NotificationDetail =itemView.findViewById(R.id.Notification_Deatils);
            NotificationImageList =itemView.findViewById(R.id.Notification_Image);

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
    public void setOnItemCLickListener(OnItemClickListener listener){
        this.listener=listener;

    }
}