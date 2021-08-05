package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class AllProductUniversal extends AppCompatActivity {
    RecyclerView recyclerView;
    AllProductUniversalAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_product_universal);
        recyclerView=findViewById(R.id.RecyclerViewAll_Product_Univerasal);
        FirebaseRecyclerOptions<AllProductUniversalModel> options =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt("Gadget").endAt("Gadget"+"\uf8ff"), AllProductUniversalModel.class)
                        .build();
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new AllProductUniversalAdapter(options);
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