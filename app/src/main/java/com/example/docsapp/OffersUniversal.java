package com.example.docsapp;

        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.GridLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.Toast;

        import com.bumptech.glide.Glide;
        import com.bumptech.glide.load.DataSource;
        import com.bumptech.glide.load.engine.GlideException;
        import com.bumptech.glide.request.RequestListener;
        import com.bumptech.glide.request.target.Target;
        import com.firebase.ui.database.FirebaseRecyclerOptions;
        import com.google.android.gms.auth.api.Auth;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.DocumentSnapshot;
        import com.google.firebase.firestore.EventListener;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firebase.firestore.FirebaseFirestoreException;

        import java.util.HashMap;
        import java.util.Map;
        import java.util.Objects;

public class OffersUniversal extends AppCompatActivity {
    RecyclerView  FAQs;
    RecyclerView SearchTopics;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    String UserID;
    GadgetsCategoryGridAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String Offer;
    String s,DefaultImageURL,TabularImageURL;
    ImageView DefaultImage,TabularImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_universal);
        SearchTopics=findViewById(R.id.OfferUniversal_Search_Topic_RecyclerView);
        FAQs=findViewById(R.id.OfferUniversal_FAQs_RecyclerView);
        LoadingDialog loadingDialog = new LoadingDialog(this);
        DefaultImage=findViewById(R.id.Offer_Default_Image_offerUniversal);
        TabularImage=findViewById(R.id.Offer_Tabular_form_offerUniversal);
        Auth=FirebaseAuth.getInstance();
        UserID=Auth.getCurrentUser().getUid();
        Store=FirebaseFirestore.getInstance();
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("OffersIntent")!=null){
                    Offer=value.getString("OffersIntent");
                }
            }
        });



        loadingDialog.startLoadingDialog();

        new CountDownTimer(1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if (Offer!=null){
                    FirebaseDatabase.getInstance().getReference().child("Fragment_Home").child("Slider_Home").child(Offer).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            DefaultImageURL =snapshot.child("Image").getValue().toString();
                            TabularImageURL=snapshot.child("TabularImage").getValue().toString();
                            Toast.makeText(OffersUniversal.this, "Loading Image....", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        }.start();

        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

                if (Offer != null) {




                    Glide.with(getApplicationContext()).load(DefaultImageURL).
                            listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    Toast.makeText(OffersUniversal.this, "Error in Loading Image", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(OffersUniversal.this, ""+DefaultImageURL, Toast.LENGTH_SHORT).show();
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    return false;
                                }
                            })
                            .into(DefaultImage);
                    Glide.with(getApplicationContext()).load(TabularImageURL).into(TabularImage);
                    FirebaseRecyclerOptions<GadgetsCategoryModel> options =
                            new FirebaseRecyclerOptions.Builder<GadgetsCategoryModel>()
                                    .setQuery(FirebaseDatabase.getInstance().getReference().child("Fragment_Home").child("Slider_Home").child(Offer).child("Category"), GadgetsCategoryModel.class)
                                    .build();

                    adapter = new GadgetsCategoryGridAdapter(options);
                    layoutManager = new GridLayoutManager(OffersUniversal.this, 2);
                    SearchTopics.setLayoutManager(layoutManager);
                    SearchTopics.setAdapter(adapter);
                    adapter.startListening();
                    loadingDialog.dismissDialog();

                    adapter.setOnItemCLickListener(new GadgetsCategoryGridAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(DataSnapshot dataSnapshot, int position) {

                            String key = dataSnapshot.getKey();
                            DatabaseReference databaseReference = database.getReference().child("Fragment_Home").child("Slider_Home").child(Offer).child("Category");

                            databaseReference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    s = (String) snapshot.child(key).child("Name").getValue();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            loadingDialog.startLoadingDialog();
                            new CountDownTimer(1000,1000){

                                @Override
                                public void onTick(long millisUntilFinished) {

                                }

                                @Override
                                public void onFinish() {
                                    if (s != null) {
                                        Map<String, Object> user = new HashMap<>();
                                        user.put("Home_Intent", s);
                                        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(@NonNull Void aVoid) {
                                                loadingDialog.dismissDialog();
                                                Intent intent = new Intent(OffersUniversal.this, AllProductUniversal.class);
                                                startActivity(intent);
                                            }
                                        });

                                    }


                                }
                            }.start();



                        }
                    });

                }
            }
        }.start();



    }
}
