package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.FirebaseDatabase;

public class AllProductUniversal extends AppCompatActivity {
    RecyclerView recyclerView;
    AllProductUniversalAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String GadgetsWorld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_product_universal);
        recyclerView=findViewById(R.id.RecyclerViewAll_Product_Univerasal);
        FirebaseRecyclerOptions<AllProductUniversalModel> options =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt("Health").endAt("Health" + "\uf8ff"), AllProductUniversalModel.class)
                        .build();
        adapter = new AllProductUniversalAdapter(options);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}