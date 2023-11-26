package com.example.watersensor;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.watersensor.databinding.ActivityMainScreenBinding;

import java.util.Map;
import java.util.Objects;

public class mainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainScreenBinding activityscreen = ActivityMainScreenBinding.inflate(getLayoutInflater());
        setContentView(activityscreen.getRoot());

//        activityscreen.ayurvedic.setOnClickListener(view -> globals.redirectActvity(mainScreen.this,showPlants.class,"Ayurvedic Plants"));
//        activityscreen.flowering.setOnClickListener(view -> globals.redirectActvity(mainScreen.this,showPlants.class,"Flowering Plants"));
//        activityscreen.office.setOnClickListener(view -> globals.redirectActvity(mainScreen.this,showPlants.class,"Office Plants"));
//        activityscreen.vastu.setOnClickListener(view -> globals.redirectActvity(mainScreen.this,showPlants.class,"Vastu Plants"));
//        activityscreen.positive.setOnClickListener(view -> globals.redirectActvity(mainScreen.this,showPlants.class,"Positive Plants"));

    }


}