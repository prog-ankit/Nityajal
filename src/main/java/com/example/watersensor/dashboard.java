package com.example.watersensor;

import static com.example.watersensor.globals.databaseReference;
import static com.example.watersensor.globals.dids;
import static com.example.watersensor.globals.dnames;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.watersensor.databinding.ActivityDashboardBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class dashboard extends AppCompatActivity {

    private ActivityDashboardBinding dashboard;
    private WifiManager wifiManager;
    private String userId;
    private dashboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dashboard = ActivityDashboardBinding.inflate(getLayoutInflater());
        setSupportActionBar(dashboard.toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
        setContentView(dashboard.getRoot());

        SessionManager sessionManager = new SessionManager(dashboard.this);
        userId = sessionManager.getUid();
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);


        dashboard.layoutDetails.setVisibility(View.GONE);
        dashboard.animatedLeaf.playAnimation();
        dnames.clear();
        dids.clear();
        check_retrieve_devices();


        if (ContextCompat.checkSelfPermission(dashboard.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.shouldShowRequestPermissionRationale(dashboard.this,
                    Manifest.permission.ACCESS_FINE_LOCATION);
            if (ActivityCompat.shouldShowRequestPermissionRationale(dashboard.this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(dashboard.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                ActivityCompat.requestPermissions(dashboard.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }

    }

    private void setDashboard() {
        dashboard.dashProgress.setVisibility(View.GONE);
        dashboard.dashProgress.pauseAnimation();
        dashboard.mainCard.setVisibility(View.VISIBLE);
        dashboard.recyclerView.setVisibility(View.GONE);
        dashboard.layoutDetails.setVisibility(View.VISIBLE);
    }

    void check_retrieve_devices() {
//        dashboard.dashProgress.setVisibility(View.VISIBLE);
        dashboard.dashProgress.playAnimation();
        dashboard.mainCard.setVisibility(View.GONE);
        databaseReference.child("userdevices").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setFlag(snapshot, snapshot.exists());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void setFlag(DataSnapshot snapshot, boolean b) {
        if (!b) setDashboard();
        else {
            for (DataSnapshot ds : snapshot.getChildren()) {
                dids.add(String.valueOf(ds.getKey()));
                dnames.add(String.valueOf(ds.getValue()));
            }
            dashboard.dashProgress.setVisibility(View.VISIBLE);
            dashboard.dashProgress.playAnimation();
            dashboard.mainCard.setVisibility(View.GONE);
            dashboard.recyclerView.setVisibility(View.GONE);
            dashboard.layoutDetails.setVisibility(View.GONE);
            dashboard.timeDetail.setVisibility(View.GONE);
            setCards(dnames, dids);
        }
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Back Again to Exit!!", Toast.LENGTH_SHORT).show();
        new Handler(Looper.getMainLooper()).postDelayed(() -> doubleBackToExitPressedOnce = false, 2000);
    }

    //    @Override
//    public void onCreateContextMenu(android.view.ContextMenu menu, View v, ContextMenuInfo menuInfo)
//    {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.contextmenu, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        switch (item.getItemId()) {
//
//            case R.id.delete:
//
//                return true;
//            default:
//                return super.onContextItemSelected(item);
//        }
    private void setCards(List<String> dnames, List<String> dids) {
        databaseReference.child("data").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dashboard.dashProgress.setVisibility(View.GONE);
                dashboard.mainCard.setVisibility(View.VISIBLE);
                dashboard.timeDetail.setVisibility(View.VISIBLE);
                dashboard.recyclerView.setVisibility(View.VISIBLE);
                List<String> status = new ArrayList<>();
                if (snapshot.getChildrenCount() != 0) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        Log.d("Dids ==>", String.valueOf(dids));
                        Log.d("Keys ==>", ds.getKey());

                        if (String.valueOf(ds.child("Data").child("Status").getValue()).equals("1"))
                            status.add("ON");
                        else
                            status.add("OFF");
                    }


                }
                if (status.size() != dids.size()) {

                    for (int i = status.size(); i < dids.size(); i++) {
                        status.add("OFF");
                    }

                }
                Log.d("Status ==>", String.valueOf(status));
                adapter = new dashboardAdapter(getApplicationContext(), dids, dnames, status);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(dashboard.this, 2, GridLayoutManager.VERTICAL, false);
                dashboard.recyclerView.setLayoutManager(gridLayoutManager);
                dashboard.recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case (R.id.logout):
                logout();
                return true;
            case (R.id.showqr):
                LocationManager lm = (LocationManager)
                        getSystemService(Context.LOCATION_SERVICE);
                boolean gps_enabled = false;
                boolean network_enabled = false;
                try {
                    gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!gps_enabled && !network_enabled) {
                    AlertDialog alertDialog = new AlertDialog.Builder(dashboard.this)
                            .setMessage("Enable your GPS!!")
                            .setPositiveButton("Settings", (paramDialogInterface, paramInt) -> startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                            .setNegativeButton("Cancel", null).setIcon(R.drawable.nityajal)
                            .show();
                    Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
                    Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
                } else {
                    if (!wifiManager.isWifiEnabled()) {
                        Toast.makeText(this, "Turn on your wifi!!", Toast.LENGTH_SHORT).show();
                        Intent panelIntent = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                            panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                        }
                        startActivity(panelIntent);

                    } else {
                        WifiInfo info = wifiManager.getConnectionInfo();
//                        Log.d("SSID ==>",info.getSSID());
                        if (!info.getSSID().equals("\"NityaJal\"")) {
//                            Log.d("Connection DEtails ==>", String.valueOf(info.getSSID()));
//                            Log.d("SSID ==>",info.getSSID());
                            Toast.makeText(this, "Get Connect to NityaJAL device", Toast.LENGTH_SHORT).show();
                        } else {
                            scanCode();
                        }


                    }
                }


                return true;
            case (R.id.wifi):
                if (!wifiManager.isWifiEnabled()) {
                    Intent panelIntent = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                        panelIntent = new Intent(Settings.Panel.ACTION_WIFI);
                    }
                    startActivity(panelIntent);

                } else
                    Toast.makeText(this, "Wifi Already Enabled!!", Toast.LENGTH_SHORT).show();

                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return false;


    }

    public void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);

        barLaucher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLaucher = registerForActivityResult(new ScanContract(), result ->
    {
        String urlContent = result.getContents();
//        Toast.makeText(this, urlContent, Toast.LENGTH_SHORT).show();
//        new SessionManager(dashboard.this).setId(urlContent);
        Intent intent = new Intent(dashboard.this, wifi_connection.class);
        intent.putExtra("deviceId", urlContent);
        startActivity(intent);

    });


    void logout() {
        globals.auth.signOut();
        new SessionManager(dashboard.this).removeUid();
        Intent intent = new Intent(dashboard.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ContextCompat.checkSelfPermission(dashboard.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}