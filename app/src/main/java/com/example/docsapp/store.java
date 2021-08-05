package com.example.docsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class store extends Fragment {
    RecyclerView StoreRecyclerView;
    StoreRecyclerAdapter adapter;
    Context context;
    DatabaseReference firebaseDatabase;
    public ArrayList<String> StoreNameList= new ArrayList<>();
    public ArrayList<String> StoreRatingList= new ArrayList<>();
    public ArrayList<Integer> StoreImageList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_store,container,false);
        StoreRecyclerView=v.findViewById(R.id.store_recycler_view);

        firebaseDatabase=FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test");
        
        StoreRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StoreNameList.add("Gagan Medical Store");
        StoreNameList.add("M.K. Medical Store");
        StoreNameList.add("New Gagan Medical Store");
        StoreRatingList.add("4.7/5");
        StoreRatingList.add("4.8/5");
        StoreRatingList.add("4.9/5");
        StoreImageList.add(R.drawable.one);
        StoreImageList.add(R.drawable.two);
        StoreImageList.add(R.drawable.three);
        adapter = new StoreRecyclerAdapter(StoreNameList,StoreRatingList, StoreImageList,getActivity());
        StoreRecyclerView.setAdapter(adapter);
        return v;


    }
}
