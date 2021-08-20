package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DetailUniversal extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ArrayList<String> Addresses= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_universal);
        Spinner SimpleSpinner = findViewById(R.id.QTY_spinner);
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(
                this,
                R.array.QTY,
                android.R.layout.simple_spinner_item
        );
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SimpleSpinner.setAdapter(Adapter);
        SimpleSpinner.setOnItemSelectedListener(this);
        Spinner Spinner = findViewById(R.id.All_Addresses);

        Addresses.add("shorya");
        Addresses.add("do Something");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item,
                        Addresses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner.setAdapter(adapter);
        Spinner.setOnItemSelectedListener(this);




        ListView lvMain;
        String[] names;
        lvMain = (ListView) findViewById(R.id.list_item);
        // here we adjust list elements choice mode
        lvMain.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        // create adapter using array from resources file
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                this, R.array.SelectPaymentMOde,
                android.R.layout.simple_list_item_single_choice);
        lvMain.setAdapter(adapter1);



        // get array from resources file
        names = getResources().getStringArray(R.array.SelectPaymentMOde);
    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {


    }
}