package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class OrdersList extends AppCompatActivity {

    FirebaseFirestore Store;
    FirebaseAuth Auth;
    RecyclerView recyclerView;
    ArrayList<OrdersModel> DataList;
    OrderRecyclerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_list);
        Auth=FirebaseAuth.getInstance();
        String UserID= Auth.getCurrentUser().getUid();
        Store= FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.orders_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DataList=new ArrayList<>();
        adapter=new OrderRecyclerAdapter(DataList);
        recyclerView.setAdapter(adapter);
        Store.collection("Users").document(UserID).collection("Products")
                .document("Ordered").collection("orders")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d:list){
                    OrdersModel obj=d.toObject(OrdersModel.class);
                    DataList.add(obj);
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(OrdersList.this, "Work....ing...", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OrdersList.this, "Error!! "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}