package com.example.watersensor;

import android.content.Intent;
import android.graphics.Paint;

import com.journeyapps.barcodescanner.CaptureActivity;

public class CaptureAct extends CaptureActivity {
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CaptureAct.this,dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
