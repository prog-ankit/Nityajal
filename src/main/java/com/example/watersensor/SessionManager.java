package com.example.watersensor;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences("user",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void saveUid(String username){
        editor.putString("log_name",username).commit();
    }
    public String getUid(){
        return sharedPreferences.getString("log_name","");
    }
    public void removeUid(){
        editor.remove("log_name").apply();
    }

}
