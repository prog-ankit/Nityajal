package com.example.watersensor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watersensor.databinding.ActivitySignupBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySignupBinding activity = ActivitySignupBinding.inflate(getLayoutInflater());
        View view = activity.getRoot();
        setContentView(view);


//        activity.goLogin.setOnClickListener(view1 -> {
//            Intent intent = new Intent(Signup.this, MainActivity.class);
//            startActivity(intent);
//        });
//
//        activity.signup.setOnClickListener(view12 -> authenticate());
    }


}