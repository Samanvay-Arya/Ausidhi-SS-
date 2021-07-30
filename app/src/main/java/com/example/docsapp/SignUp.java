package com.example.docsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    ImageButton SignUp;
    ImageButton SignIn;
    Button Email, Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        SignIn=findViewById(R.id.SignIn_Activity_Button_SignUp);
        SignUp=findViewById(R.id.SignUp_Activity_Button_SignUp);
        Email=findViewById(R.id.signUp_with_Email_SignUp);
        Phone=findViewById(R.id.signUp_with_Phone_SignUp);
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(SignUp.this, Login_1.class);
                startActivity(i);
                finish();
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(SignUp.this,RegisterWithEmail.class);
                startActivity(i);
                finish();
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SignUp.this, FirstLogin.class);
                startActivity(i);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUp.this, "You are already on SignUp Page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}