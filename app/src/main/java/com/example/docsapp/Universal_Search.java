package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Universal_Search extends AppCompatActivity {
    RecyclerView Recycler;
AllProductUniversalAdapter Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal__search);
        Recycler = (RecyclerView) findViewById(R.id.Recycler);
        Recycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<AllProductUniversalModel> Options =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product"), AllProductUniversalModel.class)
                        .build();
        Adapter = new AllProductUniversalAdapter(Options);
        Recycler.setAdapter(Adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.universalsearchmenu,menu);
        MenuItem item=menu.findItem(R.id.universalsearchmenu);
        SearchView Searchview=(SearchView)item.getActionView();
        Searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processsearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processsearch(String s) {
        FirebaseRecyclerOptions<AllProductUniversalModel> Options =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt(s).endAt(s+"\uf8ff"), AllProductUniversalModel.class)
                        .build();
        Adapter = new AllProductUniversalAdapter(Options);
        Adapter.startListening();
        Recycler.setAdapter(Adapter);
    }
}