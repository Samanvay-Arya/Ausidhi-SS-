package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class YourAccount extends AppCompatActivity {

    TextView Name, Email, Phone, City, State, PinCode,Address;
    FirebaseAuth Auth;
    FirebaseFirestore Store;
    String UserID;
    String edit;
    Dialog dialog;
    Integer item;
    Integer Apply_result=0;
    Button Name_edit, Email_edit, Phone_edit, City_edit,State_edit, PinCode_edit , Address_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_account);
        Name=findViewById(R.id.Name_Your_Account);
        Email=findViewById(R.id.Email_Your_Account);
        Phone=findViewById(R.id.Phone_Your_Account);
        City=findViewById(R.id.City_Your_Account);
        State=findViewById(R.id.State_Your_Account);
        PinCode=findViewById(R.id.PinCode_Your_Account);
        Address=findViewById(R.id.Address_Your_Account);
        Name_edit=findViewById(R.id.Name_Your_Account_edit_button);
        Email_edit=findViewById(R.id.Email_Your_Account_edit_button);
        Phone_edit=findViewById(R.id.Phone_Your_Account_edit_button);
        City_edit=findViewById(R.id.City_Your_Account_edit_button);
        State_edit=findViewById(R.id.State_Your_Account_edit_button);
        PinCode_edit=findViewById(R.id.PinCode_Your_Account_edit_button);
        Address_edit=findViewById(R.id.Address_Your_Account_edit);
        dialog=new Dialog(YourAccount.this);
        dialog.setContentView(R.layout.dialog_your_account);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        EditText Edited_text=dialog.findViewById(R.id.dialog_EditText);
        Button Apply=dialog.findViewById(R.id.dialog_apply_button);
        Button Cancel=dialog.findViewById(R.id.dialog_cancel_button);
        Auth=FirebaseAuth.getInstance();
        Store=FirebaseFirestore.getInstance();
        UserID=Auth.getCurrentUser().getUid();
        DocumentReference documentReference=Store.collection("Users").document(UserID);
        Map<String,Object> user= new HashMap<>();
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.getString("Phone") != null) {
                    Phone.setText(value.getString("Phone"));
                }
                if (value.getString("Full Name") != null) {
                    Name.setText(value.getString("Full Name"));
                }
                if (value.getString("Email") != null) {
                    Email.setText(value.getString("Email"));
                }
                if (value.getString("City")!=null){
                    City.setText(value.getString("City"));
                }
                if (value.getString("State")!=null){
                    State.setText(value.getString("State"));
                }
                if (value.getString("PinCode")!=null){
                    PinCode.setText(value.getString("PinCode"));
                }
                if (value.getString("Address")!=null){
                    Address.setText(value.getString("Address"));
                }
            }
        });
        Apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit=Edited_text.getText().toString();
                if (!TextUtils.isEmpty(edit)) {
                    if (item == 1) {
                        user.put("Full Name", edit);
                        Edited_text.setText("");
                        dialog.dismiss();
                        Apply_result=1;
                        Edited_text.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    if (item == 2) {
                        user.put("Email", edit);
                        Edited_text.setText("");
                        dialog.dismiss();
                        Apply_result=1;
                        Edited_text.setInputType(InputType.TYPE_CLASS_TEXT);
                    }
                    if (item == 3) {
                        if (edit.length()<10){
                            Edited_text.setError("Phone Number should be greater than 10 words");
                        }
                        else {
                            user.put("Phone", edit);
                            Edited_text.setText("");
                            dialog.dismiss();
                            Apply_result=1;
                        }
                    }
                    if (item == 4) {
                        if (edit.length()<6){
                            Edited_text.setError("PinCode Should be greater than 6 words");
                        }
                        else {
                            user.put("PinCode", edit);
                            Edited_text.setText("");
                            dialog.dismiss();
                            Apply_result=1;
                        }

                    }
                    if (item == 5) {
                        user.put("State", edit);
                        Edited_text.setText("");
                        dialog.dismiss();
                        Apply_result=1;
                    }
                    if (item == 6) {
                        user.put("City", edit);
                        Edited_text.setText("");
                        dialog.dismiss();
                        Apply_result=1;
                    }
                    if (item == 7) {
                        user.put("Address", edit);
                        Edited_text.setText("");
                        dialog.dismiss();
                        Apply_result=1;
                    }
                    if (Apply_result==1) {
                        documentReference.update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(@NonNull Void aVoid) {
                                Toast.makeText(YourAccount.this, "Profile is Updated successfully", Toast.LENGTH_SHORT).show();
                                Edited_text.setInputType(InputType.TYPE_CLASS_TEXT);
                            }
                        });
                    }
                }
                else if (TextUtils.isEmpty(edit)){
                    Edited_text.setError("Field is Required");
                }
            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edited_text.setInputType(InputType.TYPE_CLASS_TEXT);
                dialog.dismiss();
            }
        });

        Name_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=1;
            }
        });
        Email_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=2;
                Edited_text.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
            }
        });
        Phone_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=3;
                Edited_text.setInputType(InputType.TYPE_CLASS_PHONE);
            }
        });
        PinCode_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=4;
                Edited_text.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });
        State_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=5;
            }
        });
        City_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=6;
            }
        });
        Address_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                item=7;
            }
        });


    }
}