package com.example.watersensor;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watersensor.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
//username - abinfo2310@gmail.com
//password - 23102002


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private SessionManager sessionManager;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        activityMainBinding.progresslogin.setVisibility(View.GONE);
        auth = FirebaseAuth.getInstance();
        activityMainBinding.pots.playAnimation();
        sessionManager = new SessionManager(MainActivity.this);
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            activityMainBinding.login.setOnClickListener(view1 -> validate());
        } else {
            activityMainBinding.login.setOnClickListener(view1 -> {
                Toast.makeText(this, "Enable Mobile Data and " +
                        "Relaunch Application", Toast.LENGTH_SHORT).show();

                    }
            );
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        checkSession();
    }

    private void checkSession() {
        String userId = sessionManager.getUid();
        if (!userId.equals("")) {
            Intent intent = new Intent(MainActivity.this, dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

    }

    private void phoneValidate(String phone) {

        activityMainBinding.pots.setVisibility(View.GONE);
        activityMainBinding.progresslogin.setVisibility(View.VISIBLE);
        activityMainBinding.progresslogin.playAnimation();
        activityMainBinding.login.setVisibility(View.GONE);
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91 " + phone)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
//                                activityMainBinding.phone.setText(String.valueOf(e.getMessage()));

                                Toast.makeText(MainActivity.this, "Failed to Login!!\n"+e.getMessage(), Toast.LENGTH_SHORT).show();

                                activityMainBinding.login.setVisibility(View.VISIBLE);
                                activityMainBinding.progresslogin.pauseAnimation();
                                activityMainBinding.progresslogin.setVisibility(View.GONE);
                                activityMainBinding.pots.setVisibility(View.VISIBLE);
                                activityMainBinding.pots.playAnimation();
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                Intent intent = new Intent(MainActivity.this, otpVerification.class);
                                intent.putExtra("mobile", phone);
                                intent.putExtra("keyotp", s);

                                startActivity(intent);
                            }
                        })// Timeout and unit
                        .setActivity(this)                 // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void validate() {
        String phone = String.valueOf(activityMainBinding.phone.getText()).trim();

        if (phone.length() == 0 || phone.length() >= 11) {
            activityMainBinding.phone.setError("Invalid Phone Number");
            activityMainBinding.phone.requestFocus();
            activityMainBinding.login.setVisibility(View.VISIBLE);
        } else
            phoneValidate(phone);


    }


}