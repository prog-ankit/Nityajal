package com.example.watersensor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

import com.example.watersensor.databinding.ActivityWifiConnectionBinding;

public class wifi_connection extends AppCompatActivity {

    private ActivityWifiConnectionBinding activitywifi;
    private SessionManager session;
    private String device;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitywifi = ActivityWifiConnectionBinding.inflate(getLayoutInflater());
        setContentView(activitywifi.getRoot());
        activitywifi.webView.setWebViewClient(new Callback());
        activitywifi.webView.getSettings().setJavaScriptEnabled(true);

        activitywifi.webView.loadUrl("http://192.168.4.1");

        device = getIntent().getStringExtra("deviceId");
        session = new SessionManager(wifi_connection.this);
        activitywifi.nextSetup.setOnClickListener(view -> saveConnect());
        activitywifi.cross.setOnClickListener(view -> {
//            session.removeId();
            Intent intent = new Intent(wifi_connection.this, dashboard.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        });
    }
    private static class Callback extends WebViewClient {  //HERE IS THE MAIN CHANGE.
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return (false);
        }

    }

    private void saveConnect() {
        String wifiSSID = String.valueOf(activitywifi.wifiname.getText());
        String wifipwd = String.valueOf(activitywifi.wifipwd.getText());
        String userId = new SessionManager(wifi_connection.this).getUid();
        if (wifiSSID.isEmpty())
            activitywifi.wifiname.setError("Must enter an SSID");
        else if (wifipwd.isEmpty())
            activitywifi.wifipwd.setError("Must enter the password");
        else {
            activitywifi.webView.loadUrl("javascript:document.getElementById('ssid').value='" + wifiSSID + "';" +
                    "document.getElementById('pwd').value='" + wifipwd + "';" +
                    "document.getElementById('user').value='" + userId + "';" +
                    "document.getElementById('submit').click();");

            Intent intent = new Intent(wifi_connection.this, deviceSetup.class);
            intent.putExtra("device",device);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

}