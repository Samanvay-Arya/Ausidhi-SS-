package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RegisterWithEmail extends AppCompatActivity {
    EditText Email;
    EditText Password;
    EditText Referral;
    Button Register,verify;
    Button AlreadyRegistered;
    String Email_string;
    String Password_string;
    Integer test;
    ProgressBar progressBar;
    FirebaseAuth Auth;
    FirebaseFirestore FireStore;
    String UserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_with_email);
        Email=findViewById(R.id.Register_Email);
        Password=findViewById(R.id.Register_Password_Email);
        Referral=findViewById(R.id.Referral_Code);
        progressBar=findViewById(R.id.progressBar_Register_With_Email);
        Register=findViewById(R.id.Register_Button_register);
        AlreadyRegistered=findViewById(R.id.Already_Registered_TextView);
        verify=findViewById(R.id.button_verify_Email);
        Auth= FirebaseAuth.getInstance();
        FireStore=FirebaseFirestore.getInstance();


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email_string=Email.getText().toString();
                Password_string=Password.getText().toString();
                test=1;
                if (TextUtils.isEmpty(Email_string)){
                    Email.setError("Email is Required");
                    test=0;
                }
                if (TextUtils.isEmpty(Password_string)){
                    Password.setError("Password is Required");
                    test=0;
                }
                if (Password_string.length()< 6){
                    Password.setError("Password > 6 words");
                    test=0;
                }
                if (test==1) {
                    Register.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    Auth.createUserWithEmailAndPassword(Email_string,Password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                UserID=Auth.getCurrentUser().getUid();
                                DocumentReference documentReference=FireStore.collection("Users").document(UserID);
                                Map<String,Object> user= new HashMap<>();
                                user.put("Email" , Email_string);
                                user.put("Password",Password_string);
                                documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(@NonNull Void aVoid) {
                                        Toast.makeText(RegisterWithEmail.this, "User is created successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                Toast.makeText(RegisterWithEmail.this, "Successfully registered on AUSIDHI", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                verify.setVisibility(View.VISIBLE);

                            }
                            else{
                                Toast.makeText(RegisterWithEmail.this, "Error! "+ Objects.requireNonNull(task.getException()).getMessage() , Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                Register.setVisibility(View.VISIBLE);
                                Register.setAlpha(1);
                            }
                        }
                    });
                }
            }
        });
            verify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseUser currentUser=Auth.getCurrentUser();
                    Toast.makeText(RegisterWithEmail.this, "verify is working", Toast.LENGTH_SHORT).show();
                    currentUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(@NonNull Void aVoid) {
                            Toast.makeText(RegisterWithEmail.this, "Verification Link has been sent to your Email" +
                                    "check your Email Inbox", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(RegisterWithEmail.this, "there is an error in verifying your mail." +
                                    "Please try again"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });
        FirebaseUser currentUser=Auth.getCurrentUser();
            if (currentUser!=null && currentUser.isEmailVerified()){
                Intent i= new Intent(RegisterWithEmail.this,Login_2.class);
                startActivity(i);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }


        AlreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(RegisterWithEmail.this,LoginWithEmail.class);
                startActivity(i);
                finish();
            }
        });
    }
}