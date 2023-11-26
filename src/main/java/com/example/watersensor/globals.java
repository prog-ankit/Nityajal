package com.example.watersensor;

import android.app.Activity;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class globals {

    static String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";
    private static String deviceId = null;
    public static Integer count = null;
    static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    static FirebaseAuth auth = FirebaseAuth.getInstance();

    public static void redirectActvity(Activity activity, Class aclass, String plantName) {
        Intent intent = new Intent(activity, aclass);
        intent.putExtra("plant", plantName);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    public static void setDid(String did) {
        deviceId = did;
    }

    public static String getDid() {
        return deviceId;
    }

    public static void setCount(int ct) {
        count = ct;
    }

    static List<String> dids = new ArrayList<>();
    static List<String> dnames = new ArrayList<>();

//    public static void setId(String deviceId){
//        globals.deviceId = deviceId;
//    }
//    public static String getId(){
//        return globals.deviceId;
//    }
}
