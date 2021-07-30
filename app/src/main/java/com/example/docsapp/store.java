package com.example.docsapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class store extends Fragment {
    RecyclerView StoreRecyclerView;
    StoreRecyclerAdapter adapter;
    Context context;
    public ArrayList<String> StoreNameList= new ArrayList<>();
    public ArrayList<String> StoreRatingList= new ArrayList<>();
    public ArrayList<Integer> StoreImageList=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_store,container,false);
        StoreRecyclerView=v.findViewById(R.id.store_recycler_view);

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
