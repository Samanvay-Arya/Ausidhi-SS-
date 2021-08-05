package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class gadgets_first_page extends AppCompatActivity {
    RecyclerView recyclerView;
    GadgetsCategoryGridAdapter adapter;
    RecyclerView.LayoutManager layoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets_first_page);
        recyclerView=findViewById(R.id.GridViewGadgetsCategory);
        FirebaseRecyclerOptions<GadgetsCategoryModel> options =
                new FirebaseRecyclerOptions.Builder<GadgetsCategoryModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Fragment_Home").child("Gadgets_World").child("Category"), GadgetsCategoryModel.class)
                        .build();
        layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new GadgetsCategoryGridAdapter(options);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemCLickListener(new GadgetsCategoryGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot dataSnapshot, int position) {
                String GadgetsFirstPage= dataSnapshot.getKey();
                Intent intent=new Intent(gadgets_first_page.this,IndividualProductUniversal.class);
                intent.putExtra("GadgetsFirstPageKey",GadgetsFirstPage);
                startActivity(intent);
            }
        });

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