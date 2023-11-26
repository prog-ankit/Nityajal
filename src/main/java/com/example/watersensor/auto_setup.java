package com.example.watersensor;

import static com.example.watersensor.globals.databaseReference;
import com.example.watersensor.databinding.ActivityAutoSetupBinding;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class auto_setup extends AppCompatActivity {
    private String device;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAutoSetupBinding autoSetupbind = ActivityAutoSetupBinding.inflate(getLayoutInflater());
        setContentView(autoSetupbind.getRoot());
        device = getIntent().getStringExtra("device");
//        autoSetupbind.min1.setOnClickListener(view -> showAlertDialog(1));
//        autoSetupbind.min5.setOnClickListener(view -> showAlertDialog(5));
//        autoSetupbind.min10.setOnClickListener(view -> showAlertDialog(10));
//        autoSetupbind.min15.setOnClickListener(view -> showAlertDialog(15));
    }
    private void showAlertDialog(int duration) {
        AlertDialog.Builder builder = new AlertDialog.Builder(auto_setup.this);
        builder.setMessage("Are you to set the Timer?");
        builder.setTitle("Confirm").setIcon(R.drawable.nityajal);
        builder.setPositiveButton("Yes", (dialog, which) -> storeTime(duration));
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
    }
    private void storeTime(int duration) {
        String userId = new SessionManager(auto_setup.this).getUid();
        databaseReference.child("data").child(userId).child(device).child("Data").child("Autosetup").getRef().setValue(duration);
    }
}