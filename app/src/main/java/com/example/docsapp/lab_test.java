package com.example.docsapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.security.PrivateKey;
import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.security.PrivateKey;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import static com.example.docsapp.R.id.recyclerview;


public class lab_test extends Fragment {
   public RecyclerView RecyclerView;
   public Lab_test_recycler_Adapter Adapter;

    public ArrayList<String>LabTestNameList=new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_lab_test,container,false);
        RecyclerView=v.findViewById(R.id.lab_test_recyclerView);
        RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        FirebaseRecyclerOptions<LabTestModel> options =
                new FirebaseRecyclerOptions.Builder<LabTestModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("LabTest"), LabTestModel.class)
                        .build();
        Adapter=new Lab_test_recycler_Adapter(options);
        RecyclerView.setAdapter(Adapter);
        Adapter.setOnItemCLickListener(new Lab_test_recycler_Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(DataSnapshot dataSnapshot, int position) {
                String key= dataSnapshot.getKey();

                Intent intent= new Intent(getContext(),OrderForLabTest.class);
                intent.putExtra("Key",key);
                startActivity(intent);
            }
        });

    return v;
    }
    @Override
    public void onStart() {
        super.onStart();
        Adapter.startListening();
    }
    @Override
    public void onStop() {
        super.onStop();
        Adapter.stopListening();
    }
}