package com.example.docsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AllProductUniversal extends AppCompatActivity {
    RecyclerView recyclerView;
    AllProductUniversalAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String category;
    TextView textView;
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_product_universal);
        recyclerView=findViewById(R.id.RecyclerViewAll_Product_Univerasal);
        textView=findViewById(R.id.TextView_all_Product_Universal);
        Auth=FirebaseAuth.getInstance();
        Store=FirebaseFirestore.getInstance();
        UserID=Auth.getCurrentUser().getUid();
        LoadingDialog loadingDialog = new LoadingDialog(this);
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("Home_Intent")!=null) {
                    category = value.getString("Home_Intent").toLowerCase().trim();
                }
            }
        });
        if (category!=null){
            FirebaseRecyclerOptions<AllProductUniversalModel> options =
                    new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                            .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt(category).endAt(category + "\uf8ff"), AllProductUniversalModel.class)
                            .build();
            adapter = new AllProductUniversalAdapter(options);
            layoutManager = new GridLayoutManager(AllProductUniversal.this, 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            adapter.startListening();
            loadingDialog.dismissDialog();
        }
            loadingDialog.startLoadingDialog();

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (category!=null) {
                    FirebaseRecyclerOptions<AllProductUniversalModel> options =
                            new FirebaseRecyclerOptions.Builder<AllProductUniversalModel>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("All_Product").child("Test").orderByChild("Keyword").startAt(category).endAt(category + "\uf8ff"), AllProductUniversalModel.class)
                                    .build();
                    adapter = new AllProductUniversalAdapter(options);
                    layoutManager = new GridLayoutManager(AllProductUniversal.this, 2);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.startListening();
                    loadingDialog.dismissDialog();
                }
                else{
                    Toast.makeText(AllProductUniversal.this, "Check Your Connection and Restart Ausidhi", Toast.LENGTH_SHORT).show();
                }
            }
        }.start();




    }

}