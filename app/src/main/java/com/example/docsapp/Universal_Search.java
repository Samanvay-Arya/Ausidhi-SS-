package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Universal_Search extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> allItem;
    AllProductUniversalAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    int childCount;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universal__search);
        toolbar=findViewById(R.id.Toolbar_universal_search);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.universal_recycler_view);

        FirebaseRecyclerOptions<AllProductUniversalModel> options =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test"), AllProductUniversalModel.class)
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.universalsearchmenu,menu);
        MenuItem item=menu.findItem(R.id.UniversalSearchMenu);
        SearchView Searchview=(SearchView)item.getActionView();
        Searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                processSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processSearch(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processSearch(String s) {
        String lower=s.toLowerCase().trim();
        FirebaseRecyclerOptions<AllProductUniversalModel> Options1 =
                new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt(lower).endAt(lower+"\uf8ff"), AllProductUniversalModel.class)
                        .build();

        adapter = new AllProductUniversalAdapter(Options1);


        adapter.startListening();
        layoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


    }
}