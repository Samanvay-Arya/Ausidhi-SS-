package com.example.docsapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DirectAction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;


public class home extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE =1 ;
    public SliderView top_sliderView;
    Button call_for_orders;
    Button AskQuerry;
    ImageButton camera_prescription;
    Button AddPrescription;
    ImageButton menu;
    TextView Individual_Category_1;
    TextView Individual_Category_2;
    TextView Individual_Category_3;
    TextView Individual_Category_4;
    TextView Gadgets_Name_1;
    TextView Gadgets_Name_2;
    TextView Gadgets_Name_3;
    TextView Gadgets_Name_4;
    TextView Trending_Topic_1;
    TextView Trending_Topic_2;
    TextView Trending_Topic_3;
    TextView Trending_Topic_4;
    TextView Brand_Name_1;
    TextView Brand_Name_2;
    TextView Brand_Name_3;
    TextView Brand_Name_4;
    TextView Learn_Disease_1;
    TextView Learn_Disease_2;
    TextView Learn_Disease_3;
    TextView Learn_Disease_4;

    TextView GadgetsExploreMore;
    TextView ExploreMoreSearchCategory;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Trending_topic_1_reference = database.getReference().child("Fragment_Home");
    int[] topSliderImages = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.three,
            R.drawable.four
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        top_sliderView = v.findViewById(R.id.top_slider_view);
        camera_prescription=v.findViewById(R.id.Button_Image_Below_AskQuery);
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        ProgressBar progressBar=v.findViewById(R.id.ProgressBar);
        AddPrescription=v.findViewById(R.id.Add_Prescription_home);
        AskQuerry=v.findViewById(R.id.Ask_query_Button_home);
        call_for_orders=v.findViewById(R.id.Call_for_Orders_home);
        Trending_Topic_1 = v.findViewById(R.id.Trending_Topic_1);
        Trending_Topic_2 = v.findViewById(R.id.Trending_Topic_2);
        Trending_Topic_3 = v.findViewById(R.id.Trending_Topic_3);
        Trending_Topic_4 = v.findViewById(R.id.Trending_Topic_4);
        Individual_Category_1 = v.findViewById(R.id.Individual_Category_1);
        Individual_Category_2 = v.findViewById(R.id.Individual_Category_2);
        Individual_Category_3 = v.findViewById(R.id.Individual_Category_3);
        Individual_Category_4 = v.findViewById(R.id.Individual_Category_4);
        Gadgets_Name_1 = v.findViewById(R.id.Gadget_Name_1);
        Gadgets_Name_2 = v.findViewById(R.id.Gadget_Name_2);
        Gadgets_Name_3 = v.findViewById(R.id.Gadget_Name_3);
        Gadgets_Name_4 = v.findViewById(R.id.Gadget_Name_4);
        Brand_Name_1 = v.findViewById(R.id.Brand_Name_1);
        Brand_Name_2 = v.findViewById(R.id.Brand_Name_2);
        Brand_Name_3 = v.findViewById(R.id.Brand_Name_3);
        Brand_Name_4 = v.findViewById(R.id.Brand_Name_4);
        Learn_Disease_1 = v.findViewById(R.id.Learn_Disease_1);
        Learn_Disease_2 = v.findViewById(R.id.Learn_Disease_2);
        Learn_Disease_3 = v.findViewById(R.id.Learn_Disease_3);
        Learn_Disease_4 = v.findViewById(R.id.Learn_Disease_4);

        SliderAdapter sliderAdapter = new SliderAdapter(topSliderImages);
        top_sliderView.setSliderAdapter(sliderAdapter);
        top_sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        top_sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        top_sliderView.startAutoCycle();
        ExploreMoreSearchCategory = v.findViewById(R.id.ExploreMoreSearchCategory);
        GadgetsExploreMore = v.findViewById(R.id.GadgetsExploreMore);

        GadgetsExploreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), gadgets_first_page.class);
                startActivity(i);
            }
        });

           if (Trending_Topic_1.length() == 0) {
               loadingDialog.startLoadingDialog();

           }

      new CountDownTimer(5000,500){

          @Override
          public void onTick(long millisUntilFinished) {

             if (Trending_Topic_1.length()!=0){
                 loadingDialog.dismissDialog();
                 onStop();
             }
          }

          @Override
          public void onFinish() {
              loadingDialog.dismissDialog();
              if (Trending_Topic_1.length()==0){
              Toast.makeText(getActivity(), "Check your Connection And Restart App Again", Toast.LENGTH_SHORT).show();}
          }
      }.start();



        ExploreMoreSearchCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), search_by_category.class);
                startActivity(i);
            }
        });
        AddPrescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission
                .CAMERA)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CAMERA},1);
                }
                else {
                    Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivity(camera);
                }
            }
        });
        call_for_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission
                        .CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CALL_PHONE},1);
                }
                else {
                    Intent call = new Intent(Intent.ACTION_CALL);
                    call.setData(Uri.parse("tel:" + "8218305987"));
                    startActivity(call);
                }
            }
        });
        AskQuerry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission
                        .SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.SEND_SMS},1);
                }
                else {
                    Intent Query = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + "8218655014"));
                    startActivity(Query);
                }

            }
        });
        Trending_topic_1_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot Snapshot) {
                String Trending_Topic_1_string = (String) Snapshot.child("Trending_Topics").child("Trending_Topic_1").getValue();
                String Trending_Topic_2_string = (String) Snapshot.child("Trending_Topics").child("Trending_Topic_2").getValue();
                String Trending_Topic_3_string = (String) Snapshot.child("Trending_Topics").child("Trending_Topic_3").getValue();
                String Trending_Topic_4_string = (String) Snapshot.child("Trending_Topics").child("Trending_Topic_4").getValue();
                Trending_Topic_1.setText(Trending_Topic_1_string);
                Trending_Topic_2.setText(Trending_Topic_2_string);
                Trending_Topic_3.setText(Trending_Topic_3_string);
                Trending_Topic_4.setText(Trending_Topic_4_string);
                String Individual_Category_1_String = (String) Snapshot.child("Individual_Corner").child("Individual_Category_1").getValue();
                String Individual_Category_2_String = (String) Snapshot.child("Individual_Corner").child("Individual_Category_2").getValue();
                String Individual_Category_3_String = (String) Snapshot.child("Individual_Corner").child("Individual_Category_3").getValue();
                String Individual_Category_4_String = (String) Snapshot.child("Individual_Corner").child("Individual_Category_4").getValue();
                Individual_Category_1.setText(Individual_Category_1_String);
                Individual_Category_2.setText(Individual_Category_2_String);
                Individual_Category_3.setText(Individual_Category_3_String);
                Individual_Category_4.setText(Individual_Category_4_String);
                String Gadget_Name_1_String = (String) Snapshot.child("Gadgets_World").child("Gadget_Name_1").getValue();
                String Gadget_Name_2_String = (String) Snapshot.child("Gadgets_World").child("Gadget_Name_2").getValue();
                String Gadget_Name_3_String = (String) Snapshot.child("Gadgets_World").child("Gadget_Name_3").getValue();
                String Gadget_Name_4_String = (String) Snapshot.child("Gadgets_World").child("Gadget_Name_4").getValue();
                Gadgets_Name_1.setText(Gadget_Name_1_String);
                Gadgets_Name_2.setText(Gadget_Name_2_String);
                Gadgets_Name_3.setText(Gadget_Name_3_String);
                Gadgets_Name_4.setText(Gadget_Name_4_String);
                String Brand_Name_1_String = (String) Snapshot.child("Brand_Names").child("Brand_Name_1").getValue();
                String Brand_Name_2_String = (String) Snapshot.child("Brand_Names").child("Brand_Name_2").getValue();
                String Brand_Name_3_String = (String) Snapshot.child("Brand_Names").child("Brand_Name_3").getValue();
                String Brand_Name_4_String = (String) Snapshot.child("Brand_Names").child("Brand_Name_4").getValue();
                Brand_Name_1.setText(Brand_Name_1_String);
                Brand_Name_2.setText(Brand_Name_2_String);
                Brand_Name_3.setText(Brand_Name_3_String);
                Brand_Name_4.setText(Brand_Name_4_String);
                String Learn_Disease_1_String = (String) Snapshot.child("Learn_More").child("Learn_Disease_1").getValue();
                String Learn_Disease_2_String = (String) Snapshot.child("Learn_More").child("Learn_Disease_2").getValue();
                String Learn_Disease_3_String = (String) Snapshot.child("Learn_More").child("Learn_Disease_3").getValue();
                String Learn_Disease_4_String = (String) Snapshot.child("Learn_More").child("Learn_Disease_4").getValue();
                Learn_Disease_1.setText(Learn_Disease_1_String);
                Learn_Disease_2.setText(Learn_Disease_2_String);
                Learn_Disease_3.setText(Learn_Disease_3_String);
                Learn_Disease_4.setText(Learn_Disease_4_String);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        return v;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==1 && grantResults.length > 0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Intent camera= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(camera);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}