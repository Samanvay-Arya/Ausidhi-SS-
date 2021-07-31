package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    RecyclerView recyclerView;
    ArrayList<CartModel> DataList;
    CartRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Store=FirebaseFirestore.getInstance();
        Auth=FirebaseAuth.getInstance();
        recyclerView=findViewById(R.id.CartReyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        String UserID=Auth.getCurrentUser().getUid();
        DataList= new ArrayList<>();
        adapter=new CartRecyclerAdapter(DataList);
        recyclerView.setAdapter(adapter);
        Store.collection("Users").document(UserID).collection("Products")
                .document("CartOrders").collection("Orders").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    CartModel obj=d.toObject(CartModel.class);
                    DataList.add(obj);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(Cart.this, "Work....ing...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Cart.this, "Please check your connection" +
                        "       or" +
                        "Restart the App", Toast.LENGTH_SHORT).show();
            }
        });

    }
}