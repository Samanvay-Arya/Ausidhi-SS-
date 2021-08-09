package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.GridView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class gadgets_first_page extends AppCompatActivity {
    RecyclerView recyclerView;
    GadgetsCategoryGridAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    String UserID;
    String s;

    DatabaseReference databaseReference = database.getReference().child("Fragment_Home").child("Gadgets_World").child("Category");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets_first_page);
        recyclerView=findViewById(R.id.GridViewGadgetsCategory);
        LoadingDialog loadingDialog = new LoadingDialog(this);
        Auth=FirebaseAuth.getInstance();
        Store=FirebaseFirestore.getInstance();
        UserID=Auth.getCurrentUser().getUid();
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

                String key= dataSnapshot.getKey();

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                         s= (String) snapshot.child(key).child("Name").getValue();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (s!=null) {
                    loadingDialog.startLoadingDialog();
                    DocumentReference documentReference = Store.collection("Users").document(UserID);
                    Map<String, Object> user = new HashMap<>();
                    user.put("Home_Intent", s);
                    documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void aVoid) {
                            loadingDialog.dismissDialog();
                            Intent intent = new Intent(gadgets_first_page.this, AllProductUniversal.class);
                            startActivity(intent);

                        }
                    });

                }


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