package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.docsapp.R.anim.layout_fall_down;

public class Brands extends AppCompatActivity {
    public RecyclerView brands;
    public RecyclerAdapter adapter;
    public ArrayList<String> countryNameList = new ArrayList<>();
    public ArrayList<String> detailsList = new ArrayList<>();
    public ArrayList<Integer> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brands);
        brands = findViewById(R.id.brands);
        brands.setLayoutManager(new LinearLayoutManager(Brands.this));
        countryNameList.add("United Kingdom");
        countryNameList.add("India");
        countryNameList.add("USA");

        detailsList.add("this is United kingdom");
        detailsList.add("this is India");
        detailsList.add("this is USA");

        imageList.add(R.drawable.admin);
        imageList.add(R.drawable.cart);
        imageList.add(R.drawable.ic_doctor_svg);

        adapter = new RecyclerAdapter(countryNameList, detailsList, imageList, Brands.this);
        brands.setAdapter(adapter);
        Context context=brands.getContext();
        LayoutAnimationController layoutAnimationController=AnimationUtils.loadLayoutAnimation(context,layout_fall_down);
        brands.setLayoutAnimation(layoutAnimationController);
        Objects.requireNonNull(brands.getAdapter()).notifyDataSetChanged();
        brands.scheduleLayoutAnimation();

    }

//    private void layout_animation(RecyclerView brands) {
//        Context context = brands.getContext();
//        LayoutAnimationController layoutAnimationController= AnimationUtils.loadLayoutAnimation(context, layout_fall_down);
//        brands.setLayoutAnimation(layoutAnimationController);
//        Objects.requireNonNull(brands.getAdapter()).notifyDataSetChanged();
//        brands.scheduleLayoutAnimation();
//    }
}