package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Login_2 extends AppCompatActivity {
    EditText UserName;
    EditText UserEmail;
    EditText UserPinCode;
    EditText UserState;
    EditText UserCity;
    ImageButton Next;
    String Name_string, Email_string, PinCode_string , State_string,City_string ;
    FirebaseFirestore FireStore;
    String UserID;
    FirebaseAuth Auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_2);
        UserName=findViewById(R.id.User_Name_Login_2);
        UserEmail=findViewById(R.id.User_Email_Login_2);
        UserPinCode=findViewById(R.id.User_PinCode_Login_2);
        UserState=findViewById(R.id.User_State_Login_2);
        UserCity=findViewById(R.id.User_City_Login_2);
        Next=findViewById(R.id.Next_Login_2);
        Auth=FirebaseAuth.getInstance();
        FireStore=FirebaseFirestore.getInstance();
        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserName.getText().toString().length() != 0 && UserCity.getText().toString().length() != 0 && UserState.getText().toString().length() != 0 && UserPinCode.getText().toString().length() != 0){
                    Name_string=UserName.getText().toString();
                    Email_string=UserEmail.getText().toString();
                    PinCode_string=UserPinCode.getText().toString();
                    State_string=UserState.getText().toString();
                    City_string=UserCity.getText().toString();
                    UserID= Objects.requireNonNull(Auth.getCurrentUser()).getUid();
                    GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(Login_2.this);
                    DocumentReference documentReference=FireStore.collection("Users").document(UserID);
                    Map<String,Object> user= new HashMap<>();

                    if (signInAccount!=null && Email_string==null){
                        Email_string=signInAccount.getDisplayName();
                    }
                    user.put("Full_Name",Name_string);
                    user.put("Email",Email_string);
                    user.put("PinCode",PinCode_string);
                    user.put("State",State_string);
                    user.put("City",State_string);

                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void aVoid) {
                            Toast.makeText(Login_2.this, "Profile is Updated successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                    Intent i =new Intent(Login_2.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(Login_2.this, " 1 or more Required Fields are Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}