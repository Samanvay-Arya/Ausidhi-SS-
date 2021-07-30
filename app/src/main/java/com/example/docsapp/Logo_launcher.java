package com.example.docsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
public class Logo_launcher extends AppCompatActivity {
    FirebaseAuth Auth;
    @Override
    public void onStart() {
        super.onStart();
        new CountDownTimer(500,500){

            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                FirebaseUser currentUser = Auth.getCurrentUser();
                if (currentUser!=null){
                    Intent i=new Intent(Logo_launcher.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Intent i = new Intent(Logo_launcher.this,FirstLogin.class);
                    startActivity(i);
                    finish();
                }

            }
        }.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_launcher);
        Auth=FirebaseAuth.getInstance();
    }
}