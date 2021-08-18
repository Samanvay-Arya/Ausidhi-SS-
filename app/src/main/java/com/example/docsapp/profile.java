package com.example.docsapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.errorprone.annotations.SuppressPackageLocation;
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
import com.google.firebase.firestore.auth.User;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.example.docsapp.R.id.fragment_container_view_tag;


public class profile extends Fragment {
    TextView User_Name;
    ImageView User_Profile_Image;
    Button Edit_Profile_Button;
    CardView YourAccount,DoctorAppointment,Notifications,YourCart,YourOrders,SavedMedicines,SelectLanguage;
    BottomNavigationView bottomNavigationView;
    FirebaseFirestore Store;
    FirebaseAuth Auth;
    String UserID;
    ImageButton Upload;

    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference User_Name_reference=database.getReference().child("User_Details");

    @Nullable
    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_profile,container,false);
        User_Name=v.findViewById(R.id.User_Name_profile_TextView);
        Store = FirebaseFirestore.getInstance();
        Auth=FirebaseAuth.getInstance();
        User_Profile_Image=v.findViewById(R.id.User_Profile_Image);
        Edit_Profile_Button=v.findViewById(R.id.Edit_Profile_Button_profile);
        YourAccount=v.findViewById(R.id.yourAccount);
        bottomNavigationView=v.findViewById(R.id.bottom_navigation_bar);
        SelectLanguage=v.findViewById(R.id.Select_Language_Button_Profile);
        SavedMedicines=v.findViewById(R.id.Saved_Medicines_Button_Profile);
        YourOrders=v.findViewById(R.id.Your_Orders_Profile);
        YourCart=v.findViewById(R.id.Your_Cart_Profile);
        Notifications=v.findViewById(R.id.Notifications_Profile);
        DoctorAppointment=v.findViewById(R.id.Doctors_Appointment_orders_Profile);

        UserID= Objects.requireNonNull(Auth.getCurrentUser()).getUid();
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        documentReference.addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                assert value != null;
                if (value.getString("Full Name")!=null){
                    User_Name.setText(value.getString("Full Name"));
                }
            }
        });

        YourAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( getActivity(),YourAccount.class);
                startActivity(i);
            }
        });
        SelectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getActivity(),DetailUniversal.class);
                startActivity(i);
            }
        });
        SavedMedicines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Saved Medicines", Toast.LENGTH_SHORT).show();
            }
        });

        YourOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent orders= new Intent(getActivity(),OrdersList.class);
                startActivity(orders);
            }
        });
        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent notification= new Intent(getActivity(),AllNotificationsActivity.class);
                startActivity(notification);
            }
        });
        DoctorAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        YourCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent cart= new Intent(getActivity(),Cart.class);
               startActivity(cart);
            }
        });



         User_Name_reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

         Edit_Profile_Button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i= new Intent(getActivity(), YourAccount.class);
                 startActivity(i);
             }
         });

        return v;
    }

}