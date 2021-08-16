package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.w3c.dom.Text;

import java.util.Objects;

public class IndividualProductUniversal extends AppCompatActivity {

    TextView Name, Price, TotalRatings,StarRating,MRP, Description,Discount;

    SliderView sliderView;
    DatabaseReference databaseReference;
//    int[] topSliderImages = {
//            R.drawable.logo_name,
//            R.drawable.logo_name,
//            R.drawable.ic_logo_full,
//            R.drawable.logo_name
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_product_universal);
        sliderView = findViewById(R.id.slider_view_individual_product);

        // Definition of all component of the xml file.

        Name=findViewById(R.id.Product_Name_Individual_product_Universal);
        Price=findViewById(R.id.Product_Price_Individual_product_Universal);
        TotalRatings=findViewById(R.id.Product_Total_Ratings_Individual_product_Universal);
        StarRating=findViewById(R.id.Product_Start_Rating_Individual_product_Universal);
        MRP=findViewById(R.id.Product_MRP_Individual_product_Universal);
        Description=findViewById(R.id.Product_Description_Individual_product_Universal);
        Discount=findViewById(R.id.Product_Discount_Individual_product_Universal);

//        Integer image;
//        databaseReference= FirebaseDatabase.getInstance().getReference().child("Fragment_Home").child("Gadgets_World").child("Category");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
////                String text= Objects.requireNonNull(snapshot.child("Name").getValue()).toString();
////                textView.setText(text);
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//        SliderAdapter sliderAdapter = new SliderAdapter();
//        sliderView.setSliderAdapter(sliderAdapter);
//        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
//        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
//        sliderView.startAutoCycle();

    }
}