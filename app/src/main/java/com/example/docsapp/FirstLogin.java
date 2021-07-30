package com.example.docsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;




public class FirstLogin extends AppCompatActivity {



    ImageButton SignUp;
    ImageButton SignIn;
    Button Phone;
    Button Email_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_login);
       SignIn=findViewById(R.id.SignIn_Activity_Button);
       SignUp=findViewById(R.id.SignUp_Activity_Button);
       Phone=findViewById(R.id.signIn_with_Phone_SignIn);
       Email_Login=findViewById(R.id.signIn_with_Email_First_Login);
       Email_Login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i= new Intent(FirstLogin.this,LoginWithEmail.class);
               startActivity(i);
               finish();
           }
       });
       Phone.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i= new Intent(FirstLogin.this,Login_1.class);
               startActivity(i);
           }
       });
       SignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(FirstLogin.this, SignUp.class);
               startActivity(i);
               finish();
           }
       });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstLogin.this, "You are already on SignIn Page", Toast.LENGTH_SHORT).show();
            }
        });

    }
}