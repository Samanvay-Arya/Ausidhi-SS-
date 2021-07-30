package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import java.util.ArrayList;
import java.util.Objects;

import static com.example.docsapp.R.anim.card_animation;
import static com.example.docsapp.R.anim.layout_fall_down;
import static com.example.docsapp.R.id.recyclerview;

public class search_by_category extends AppCompatActivity {
   public RecyclerView recyclerView;
   public CategoryRecyclerAdapter adapter;
    public ArrayList<String>CategoryName=new ArrayList<>();
    public ArrayList<String>CategoryDetail=new ArrayList<>();
    public ArrayList<Integer>CategoryImage=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_category);
        recyclerView=findViewById(recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(search_by_category.this));
        CategoryName.add("pain relief");
        CategoryName.add("fever");
        CategoryName.add("headache");
        CategoryName.add("constipation");
        CategoryName.add("constipation");
        CategoryName.add("constipation");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");



        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryName.add("pain relief");
        CategoryName.add("fever");
        CategoryName.add("headache");
        CategoryName.add("constipation");
        CategoryName.add("constipation");
        CategoryName.add("constipation");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");
        CategoryDetail.add("Linear interpolation is used in part programming to make a straight cutting motion from the cutter start position to its end position. It always uses the shortest distance a cutting tool path can take. ");



        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        CategoryImage.add(R.drawable.pain_relief);
        adapter=new CategoryRecyclerAdapter(CategoryName,CategoryDetail,CategoryImage,search_by_category.this);
        recyclerView.setAdapter(adapter);



    }
}