package com.example.watersensor;

import static com.example.watersensor.globals.databaseReference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.watersensor.databinding.ActivityDeviceSetupBinding;

public class deviceSetup extends AppCompatActivity {
    private ActivityDeviceSetupBinding deviceSetup;
    private String deviceId, userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        deviceSetup = ActivityDeviceSetupBinding.inflate(getLayoutInflater());
        setContentView(deviceSetup.getRoot());

        deviceSetup.progresssave.setVisibility(View.GONE);
        SessionManager session = new SessionManager(deviceSetup.this);
//        deviceId = session.getId();
        deviceId = getIntent().getStringExtra("device");
        userId = session.getUid();
        deviceSetup.deviceid.setText(deviceId);
        deviceSetup.saveData.setOnClickListener(v -> saveData());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(deviceSetup.this, dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void saveData() {
        deviceSetup.progresssave.setVisibility(View.VISIBLE);
        deviceSetup.progresssave.playAnimation();

        deviceSetup.saveData.setVisibility(View.GONE);
        String name = String.valueOf(deviceSetup.deviceName.getText());
        if (name.isEmpty() || name.length() < 3)
            deviceSetup.deviceName.setError("Name must be of length 4!");
        else {
            databaseReference.child("userdevices").child(userId).child(deviceId).setValue(name).addOnCompleteListener(task -> {
                deviceSetup.progresssave.setVisibility(View.GONE);
                deviceSetup.saveData.setVisibility(View.VISIBLE);
                if (task.isSuccessful()) {
                    Toast.makeText(deviceSetup.this, "Device Registered!!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(deviceSetup.this, dashboard.class);
                    intent.putExtra("device_name", name);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                } else Toast.makeText(deviceSetup.this, "Failed to Register!!" +
                            "Retry Adding Device", Toast.LENGTH_SHORT).show();

            });
        }
    }
}