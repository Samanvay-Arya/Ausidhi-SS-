package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class AllNotificationsActivity extends AppCompatActivity {
    public androidx.recyclerview.widget.RecyclerView RecyclerView;
    public Notification_Recycler_Adapter Adapter;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_notifications);
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Notifications");
        RecyclerView=findViewById(R.id.Notification_Recycler_view);
        RecyclerView.setLayoutManager(new LinearLayoutManager(AllNotificationsActivity.this));
        FirebaseRecyclerOptions<NotificationModel> options =
                new FirebaseRecyclerOptions.Builder<NotificationModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notifications"), NotificationModel.class)
                        .build();
        Adapter=new Notification_Recycler_Adapter(options);
        RecyclerView.setAdapter(Adapter);
        Adapter.setOnItemCLickListener(new Notification_Recycler_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot dataSnapshot, int position) {
                String key= dataSnapshot.getKey();
                Intent intent= new Intent(getApplicationContext(),Individual_Notification.class);
                intent.putExtra("Key",key);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }
}