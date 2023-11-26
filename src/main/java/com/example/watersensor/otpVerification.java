package com.example.watersensor;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.watersensor.databinding.ActivityOtpVerificationBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Objects;


public class otpVerification extends AppCompatActivity {
    ActivityOtpVerificationBinding activityOtp;
    private String backotp;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityOtp = ActivityOtpVerificationBinding.inflate(getLayoutInflater());
        View view = activityOtp.getRoot();
        setContentView(view);
        activityOtp.progressotp.setVisibility(View.GONE);

        sessionManager = new SessionManager(otpVerification.this);
        startTimer();
        String mobile = getIntent().getStringExtra("mobile");
//        mail = getIntent().getStringExtra("mail");
//        pwd = getIntent().getStringExtra("pwd");
//        Toast.makeText(this, mail + " : " + pwd, Toast.LENGTH_SHORT).show();
        backotp = getIntent().getStringExtra("keyotp");
        activityOtp.showmob.setText("+91 " + mobile);
        activityOtp.verify.setOnClickListener(view1 -> setInputs());

    }

    private void startTimer() {

        new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                activityOtp.timer.setText(" (" + (millisUntilFinished / 1000) + ")");
                // logic to set the EditText could go here
            }

            public void onFinish() {
                activityOtp.timer.setText("");
                activityOtp.resendotp.setClickable(true);
                activityOtp.resendotp.setOnClickListener(view -> {
                    resend();
                    startTimer();
                    activityOtp.resendotp.setClickable(false);
                });

            }

        }.start();
    }

    private void setInputs() {
        String enteredotp = String.valueOf(activityOtp.otp.getText());
        if (enteredotp.length() != 6) {
            activityOtp.otp.setError("Enter the 6-digit OTP");
            activityOtp.otp.requestFocus();
        } else {
            if (backotp != null) {
                activityOtp.progressotp.setVisibility(View.VISIBLE);
                activityOtp.progressotp.playAnimation();
                activityOtp.verify.setVisibility(View.GONE);
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(
                        backotp, enteredotp
                );

                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener(task -> {

                    activityOtp.progressotp.setVisibility(View.GONE);
                    activityOtp.verify.setVisibility(View.VISIBLE);

                    if (task.isSuccessful()) {
                        String userId = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();

                        sessionManager.saveUid(userId);
                        Toast.makeText(otpVerification.this, "User Registered!!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(otpVerification.this, dashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);


                    } else {
                        Toast.makeText(otpVerification.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                        activityOtp.otp.setText("");
                        activityOtp.otp.requestFocus();
                    }
                });
            }
        }


    }

    private void resend() {
        Log.d("resendcheck ==>", "Clicked Here");
    }


}