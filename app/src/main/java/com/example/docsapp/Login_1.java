package com.example.docsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;
import static com.example.docsapp.R.layout.activity_login_1;

public class Login_1 extends AppCompatActivity {
    ImageButton next;
    Integer RC_SIGN_IN=1;
    Button GoogleSignIN;
    GoogleSignInClient GoogleSignInClient;
    CountryCodePicker countryCodePicker;
    EditText EditTextPhoneNumber;
    FirebaseAuth Auth;
    EditText OTP;
    Button GetOTP;
    String otpid;
    String Email;
    String UserPhoneNumber;
    Button Email_SignIn;
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    TextView TVTimer;
    int timer=60;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login_1);
        EditTextPhoneNumber=findViewById(R.id.User_Phone_number_login);
        next=findViewById(R.id.login_1_next_button);
        Email_SignIn=findViewById(R.id.Phone_signIn_Email_Button);
        countryCodePicker=findViewById(R.id.country_code);
        countryCodePicker.registerCarrierNumberEditText(EditTextPhoneNumber);
        GoogleSignIN=findViewById(R.id.signIn_with_Google);
        Auth=FirebaseAuth.getInstance();
        OTP=findViewById(R.id.editTextOTP);
        GetOTP=findViewById(R.id.Get_OTP_button);
        TVTimer=findViewById(R.id.Login_WIth_Phone_Timer);
        progressBar=findViewById(R.id.Progress_Bar_Login_With_Phone);
        createRequest();
        Email_SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Login_1.this, RegisterWithEmail.class);
                startActivity(i);
                finish();
            }
        });
        GoogleSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInWithGoogle();
            }
        });
        GetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPhoneNumber= countryCodePicker.getFullNumberWithPlus().replace(" ","");
                initiateOTP();
                GetOTP.setVisibility(View.GONE);
               progressBar.setVisibility(View.VISIBLE);
                new CountDownTimer(60000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        TVTimer.setText(timer);
                        timer-=1;
                    }

                    @Override
                    public void onFinish() {
                      GetOTP.setVisibility(View.VISIBLE);
                      progressBar.setVisibility(View.GONE);
                    }
                }.start();
            }
        });

      next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(OTP.getText().toString().isEmpty())
                    Toast.makeText(getApplicationContext(),"Blank field can not be processed" ,Toast.LENGTH_LONG).show();
                else if(OTP.getText().toString().length()!=6)
                    Toast.makeText(getApplicationContext(),"INVALID OTP" ,Toast.LENGTH_LONG).show();

                else{
                    PhoneAuthCredential credential= PhoneAuthProvider.getCredential(otpid , OTP.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }

            }
        });

    }



    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))

                .requestEmail()
                .build();
        GoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }
    private void signInWithGoogle() {
        Intent signInIntent = GoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//         Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
//                 Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        }
    }

   private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        Auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = Auth.getCurrentUser();
                            Intent i= new Intent(Login_1.this,Login_2.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login_1.this, "Sorry! Please try Again Now!! ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initiateOTP() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                String.valueOf(UserPhoneNumber),
                60,
//                 Phone number to verify
           TimeUnit.SECONDS,
                this,// Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        otpid = s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signInWithPhoneAuthCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

                    }

                });
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        Auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Intent i = new Intent(Login_1.this, Login_2.class);
                            startActivity(i);

                            finish();

                            // Update UI
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Invalid OTP , Try Again", Toast.LENGTH_LONG).show();

                            // The verification code entered was invalid
                        }
                    }

                });

    }

}