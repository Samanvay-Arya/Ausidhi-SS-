package com.example.docsapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class home extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE =1 ;
    ImageSlider top_sliderView;
    String intentUniversal;
    String ExploreMoreIntent,OffersIntent;
    Button call_for_orders,SearchUniversal;
    Button AskQuerry;
    ImageButton camera_prescription;
    Button AddPrescription;
    TextView Individual_Category_1;
    TextView Individual_Category_2;
    TextView Individual_Category_3;
    TextView Individual_Category_4;

    Button Individual_Category_1_Button;
    Button Individual_Category_2_Button;
    Button Individual_Category_3_Button;
    Button Individual_Category_4_Button;

    TextView Gadgets_Name_1;
    TextView Gadgets_Name_2;
    TextView Gadgets_Name_3;
    TextView Gadgets_Name_4;

    Button Gadgets_Name_1_Button;
    Button Gadgets_Name_2_Button;
    Button Gadgets_Name_3_Button;
    Button Gadgets_Name_4_Button;

    TextView Trending_Topic_1;
    TextView Trending_Topic_2;
    TextView Trending_Topic_3;
    TextView Trending_Topic_4;

    Button Trending_Topic_1_Button;
    Button Trending_Topic_2_Button;
    Button Trending_Topic_3_Button;
    Button Trending_Topic_4_Button;

    TextView Brand_Name_1;
    TextView Brand_Name_2;
    TextView Brand_Name_3;
    TextView Brand_Name_4;

    Button Brand_Name_1_Button;
    Button Brand_Name_2_Button;
    Button Brand_Name_3_Button;
    Button Brand_Name_4_Button;

    TextView Learn_Disease_1;
    TextView Learn_Disease_2;
    TextView Learn_Disease_3;
    TextView Learn_Disease_4;
    TextView GadgetsExploreMore,ExploreMoreBrands,IndividualExploreMore;
    Button TrendingExploreMore;
    TextView ExploreMoreSearchCategory;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference Trending_topic_1_reference = database.getReference().child("Fragment_Home");
    List<SlideModel> sliderImages=new ArrayList<SlideModel>();
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    String UserID;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        Store = FirebaseFirestore.getInstance();
        Auth=FirebaseAuth.getInstance();
        UserID= Objects.requireNonNull(Auth.getCurrentUser()).getUid();
        IndividualExploreMore=v.findViewById(R.id.Individual_corner_ExploreMore);
        top_sliderView = v.findViewById(R.id.Top_Slider_View);
        camera_prescription=v.findViewById(R.id.Button_Image_Below_AskQuery);
        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        AddPrescription=v.findViewById(R.id.Add_Prescription_home);
        AskQuerry=v.findViewById(R.id.Ask_query_Button_home);
        call_for_orders=v.findViewById(R.id.Call_for_Orders_home);
        ExploreMoreBrands=v.findViewById(R.id.ExploreMoreBrands);
        Trending_Topic_1 = v.findViewById(R.id.Trending_Topic_1);
        Trending_Topic_2 = v.findViewById(R.id.Trending_Topic_2);
        Trending_Topic_3 = v.findViewById(R.id.Trending_Topic_3);
        Trending_Topic_4 = v.findViewById(R.id.Trending_Topic_4);

        Trending_Topic_1_Button = v.findViewById(R.id.Trending_Topic_1_Button);
        Trending_Topic_2_Button = v.findViewById(R.id.Trending_Topic_2_Button);
        Trending_Topic_3_Button = v.findViewById(R.id.Trending_Topic_3_Button);
        Trending_Topic_4_Button = v.findViewById(R.id.Trending_Topic_4_Button);

        Individual_Category_1 = v.findViewById(R.id.Individual_Category_1);
        Individual_Category_2 = v.findViewById(R.id.Individual_Category_2);
        Individual_Category_3 = v.findViewById(R.id.Individual_Category_3);
        Individual_Category_4 = v.findViewById(R.id.Individual_Category_4);

        Individual_Category_1_Button = v.findViewById(R.id.Individual_Category_1_Button);
        Individual_Category_2_Button = v.findViewById(R.id.Individual_Category_2_Button);
        Individual_Category_3_Button = v.findViewById(R.id.Individual_Category_3_Button);
        Individual_Category_4_Button = v.findViewById(R.id.Individual_Category_4_Button);

        Gadgets_Name_1 = v.findViewById(R.id.Gadget_Name_1);
        Gadgets_Name_2 = v.findViewById(R.id.Gadget_Name_2);
        Gadgets_Name_3 = v.findViewById(R.id.Gadget_Name_3);
        Gadgets_Name_4 = v.findViewById(R.id.Gadget_Name_4);

        Gadgets_Name_1_Button = v.findViewById(R.id.Gadget_Name_1_Button);
        Gadgets_Name_2_Button = v.findViewById(R.id.Gadget_Name_2_Button);
        Gadgets_Name_3_Button = v.findViewById(R.id.Gadget_Name_3_Button);
        Gadgets_Name_4_Button = v.findViewById(R.id.Gadget_Name_4_Button);

        Brand_Name_1 = v.findViewById(R.id.Brand_Name_1);
        Brand_Name_2 = v.findViewById(R.id.Brand_Name_2);
        Brand_Name_3 = v.findViewById(R.id.Brand_Name_3);
        Brand_Name_4 = v.findViewById(R.id.Brand_Name_4);

        Brand_Name_1_Button = v.findViewById(R.id.Brand_Name_1_Button);
        Brand_Name_2_Button = v.findViewById(R.id.Brand_Name_2_Button);
        Brand_Name_3_Button = v.findViewById(R.id.Brand_Name_3_Button);
        Brand_Name_4_Button = v.findViewById(R.id.Brand_Name_4_Button);

        Learn_Disease_1 = v.findViewById(R.id.Learn_Disease_1);
        Learn_Disease_2 = v.findViewById(R.id.Learn_Disease_2);
        Learn_Disease_3 = v.findViewById(R.id.Learn_Disease_3);
        Learn_Disease_4 = v.findViewById(R.id.Learn_Disease_4);
        SearchUniversal=v.findViewById(R.id.Universal_Search_Home);
        ExploreMoreSearchCategory = v.findViewById(R.id.ExploreMoreSearchCategory);
        GadgetsExploreMore = v.findViewById(R.id.GadgetsExploreMore);
        TrendingExploreMore=v.findViewById(R.id.Trending_ExploreMore);
        Trending_topic_1_reference.child("Slider_Home").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    sliderImages.add(new SlideModel(Objects.requireNonNull(dataSnapshot.child("Image").getValue()).toString(), Objects.requireNonNull(dataSnapshot.child("Title").getValue()).toString(),ScaleTypes.FIT));
                }
                top_sliderView.setImageList(sliderImages,ScaleTypes.FIT);
                top_sliderView.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {
                        OffersIntent=sliderImages.get(i).getTitle();
                        offersIntentUniversal();
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        SearchUniversal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),Universal_Search.class);
                startActivity(intent);
            }
        });
        IndividualExploreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreMoreIntent="Individual_Corner";
                ExploreMoreSendIntent();
            }
        });
        TrendingExploreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreMoreIntent="Trending_Topics";
                ExploreMoreSendIntent();
            }
        });
        GadgetsExploreMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreMoreIntent="Gadgets_World";
                ExploreMoreSendIntent();
            }
        });
        ExploreMoreBrands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExploreMoreIntent="Brand_Names";
                ExploreMoreSendIntent();
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
                if (ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),Manifest.permission
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
                if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission
                        .CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(),new String[] {Manifest.permission.CALL_PHONE},1);
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
                if (ContextCompat.checkSelfPermission(requireActivity(),Manifest.permission
                        .SEND_SMS)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(requireActivity(),new String[] {Manifest.permission.SEND_SMS},1);
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


        Trending_Topic_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             intentUniversal=Trending_Topic_1.getText().toString();
             sendIntent();
            }
        });
        Trending_Topic_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Trending_Topic_2.getText().toString();
                sendIntent();
            }
        });
        Trending_Topic_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Trending_Topic_3.getText().toString();
                sendIntent();
            }
        });
        Trending_Topic_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Trending_Topic_4.getText().toString();
                sendIntent();
            }
        });
        Gadgets_Name_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Gadgets_Name_1.getText().toString();
                sendIntent();
            }
        });
        Gadgets_Name_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Gadgets_Name_2.getText().toString();
                sendIntent();
            }
        });
        Gadgets_Name_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Gadgets_Name_3.getText().toString();
                sendIntent();
            }
        });
        Gadgets_Name_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Gadgets_Name_4.getText().toString();
                sendIntent();
            }
        });
        Individual_Category_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Individual_Category_1.getText().toString();
                sendIntent();
            }
        });
        Individual_Category_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Individual_Category_2.getText().toString();
                sendIntent();
            }
        });
        Individual_Category_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Individual_Category_3.getText().toString();
                sendIntent();
            }
        });
        Individual_Category_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Individual_Category_4.getText().toString();
                sendIntent();
            }
        });

        Brand_Name_1_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Brand_Name_1.getText().toString();
                sendIntent();
            }
        });
        Brand_Name_2_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Brand_Name_2.getText().toString();
                sendIntent();
            }
        });
        Brand_Name_3_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Brand_Name_3.getText().toString();
                sendIntent();
            }
        });
        Brand_Name_4_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentUniversal=Brand_Name_4.getText().toString();
                sendIntent();
            }
        });
        return v;
    }

    private void offersIntentUniversal() {
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        Map<String,Object> user= new HashMap<>();
        user.put("OffersIntent",OffersIntent);
        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void aVoid) {
            }
        });
        Intent i=new Intent(getActivity(),OffersUniversal.class);
        startActivity(i);
    }

    private void sendIntent() {
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        Map<String,Object> user= new HashMap<>();
        user.put("Home_Intent",intentUniversal);
        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void aVoid) {

            }
        });
        Intent i=new Intent(getActivity(),AllProductUniversal.class);
        startActivity(i);
    }
    private void ExploreMoreSendIntent() {
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        Map<String,Object> user= new HashMap<>();
        user.put("Explore_More_Intent",ExploreMoreIntent);
        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(@NonNull Void aVoid) {

            }
        });
        Intent i=new Intent(getActivity(),gadgets_first_page.class);
        startActivity(i);
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