package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class LoginWithEmail extends AppCompatActivity {
    Button Not_Registered;
    ProgressBar progressBar;
    Button Login,Forget_password;
    FirebaseAuth Auth;
    EditText Email;
    EditText Password;
    String Email_string;
    String Password_string;
    Integer test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_with_email);
        Not_Registered=findViewById(R.id.Not_Registered_LogIn_Email);
        Login=findViewById(R.id.SignIn_With_Email_Login_Button);
        Email=findViewById(R.id.Email_Login_EditText);
        progressBar=findViewById(R.id.progressBar_LogIn_With_Email);
        Password=findViewById(R.id.Password_Email_LogIn);
        Forget_password=findViewById(R.id.Forget_Password_LogIn_Email);
        Auth= FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
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
                    Login.setAlpha(0);
                    progressBar.setVisibility(View.VISIBLE);
                    Auth.signInWithEmailAndPassword(Email_string,Password_string).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LoginWithEmail.this, "Successfully Logged In to AUSIDHI", Toast.LENGTH_SHORT).show();
                                Intent i= new Intent(LoginWithEmail.this,MainActivity.class);
                                startActivity(i);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginWithEmail.this, "Error! "+ Objects.requireNonNull(task.getException()).getMessage() , Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                                Login.setAlpha(1);
                            }
                        }
                    });
                }
            }
        });
        Not_Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginWithEmail.this,RegisterWithEmail.class);
                startActivity(i);
                finish();
            }
        });
        Forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  EditText resetMail=new EditText(v.getContext());
                final AlertDialog.Builder PasswordResetDialog= new AlertDialog.Builder((v.getContext()));
                PasswordResetDialog.setTitle("Reset PassWord");
                PasswordResetDialog.setMessage("Enter Your Email to receive reset link");
                PasswordResetDialog.setView(resetMail);
                PasswordResetDialog.setPositiveButton("Send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String Mail=resetMail.getText().toString();
                        Auth.sendPasswordResetEmail(Mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void aVoid) {
                                Toast.makeText(LoginWithEmail.this, "Reset Link Sent to your Email", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginWithEmail.this, "Error! Please try Again " +e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                PasswordResetDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PasswordResetDialog.create().dismiss();
                    }
                });
                PasswordResetDialog.create().show();
            }
        });

    }
}