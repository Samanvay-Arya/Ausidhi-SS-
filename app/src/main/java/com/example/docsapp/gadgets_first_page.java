package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class gadgets_first_page extends AppCompatActivity {
    GridView gridView;
    GadgetsCategoryGridAdapter adapter;
    ArrayList<String> GadgetsCategoryNameList = new ArrayList<>();
    ArrayList<Integer> GadgetsCategoryImageList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadgets_first_page);
        gridView=findViewById(R.id.GridViewGadgetsCategory);
        CategoryListFilling();
        adapter= new GadgetsCategoryGridAdapter(this,GadgetsCategoryNameList,GadgetsCategoryImageList);
        gridView.setAdapter(adapter);
    }
    public void CategoryListFilling() {
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);
        GadgetsCategoryImageList.add(R.drawable.admin);

        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");
        GadgetsCategoryNameList.add("Health Monitoring");


    }
}