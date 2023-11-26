package com.example.watersensor;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.watersensor.databinding.ActivityAlarmTimerBinding;
import com.google.android.material.tabs.TabLayout;

public class alarm_timer extends AppCompatActivity{

    private ActivityAlarmTimerBinding alarmActivity;
//    private String did, daysWeak = "";
//    private AlarmManager alarm;
//    private ToggleButton tM;
//    private ToggleButton tT;
//    private ToggleButton tW;
//    private ToggleButton tTh;
//    private ToggleButton tF;
//    private ToggleButton tSa;
//    private ToggleButton tSu;
//    private int mHour, mMinute, btnpress = 0;
//    private String userId;
//    private SessionManager sessionManager;

    private TabLayout tabs;
    private ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alarmActivity = ActivityAlarmTimerBinding.inflate(getLayoutInflater());
        setContentView(alarmActivity.getRoot());

        FAdapter pageAdapter = new FAdapter(this);
        alarmActivity.viewPager.setAdapter(pageAdapter);
        alarmActivity.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                alarmActivity.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        sessionManager = new SessionManager(alarm_timer.this);
//        Button set1 = findViewById(R.id.time1);
//        set1.setOnClickListener(this);
//        Button set2 = findViewById(R.id.time2);
//        set2.setOnClickListener(this);
//        Button set3 = findViewById(R.id.time3);
//        set3.setOnClickListener(this);


//        tM = findViewById(R.id.tM);
//        tT = findViewById(R.id.tT);
//        tW = findViewById(R.id.tW);
//        tTh = findViewById(R.id.tTh);
//        tF = findViewById(R.id.tF);
//        tSa = findViewById(R.id.tSa);
//        tSu = findViewById(R.id.tSu);
//        alarm = (AlarmManager) getSystemService(ALARM_SERVICE);
//        toggle_day();

//        Log.d("Count is==>", String.valueOf(count));
//        if (count == null)
//            checkCount();
        //            Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        //            if (count == 0) {
        //                alarmActivity.time1.setClickable(true);
        //                alarmActivity.time2.setClickable(false);
        //                alarmActivity.time3.setClickable(false);
        ////                Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        ////                alarmActivity.counts.setText("3");
        //            } else if (count == 1) {
        //                alarmActivity.time1.setClickable(false);
        //                alarmActivity.time2.setClickable(true);
        //                alarmActivity.time3.setClickable(false);
        ////                Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        ////                alarmActivity.counts.setText("2");
        //            } else if (count == 2) {
        //                alarmActivity.time1.setClickable(false);
        //                alarmActivity.time2.setClickable(false);
        //                alarmActivity.time3.setClickable(true);
        ////                alarmActivity.counts.setText("1");
        //            } else {
        //                alarmActivity.time1.setClickable(false);
        //                alarmActivity.time2.setClickable(false);
        //                alarmActivity.time3.setClickable(false);
        ////                alarmActivity.counts.setText("0");
        //            }


//        alarmActivity.time1.setOnClickListener(v -> showTimer());
//        alarmActivity.time2.setOnClickListener(v -> showTimer());
//        alarmActivity.time3.setOnClickListener(v -> showTimer());
//        alarmActivity.autosetup.setOnClickListener(v -> {
//            Intent intent = new Intent(alarm_timer.this, auto_setup.class);
//            intent.putExtra("device", did);
//            startActivity(intent);
//        });

//        alarmActivity.dstatbtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//            int val;
//            if (isChecked) val = 0;
//            else val = 1;
//            globals.databaseReference.child("data").child(userId).child(did).child("Data").child("Status").getRef().setValue(val);
//
//        });
//        alarmActivity.save.setOnClickListener(view -> {
//            if (String.valueOf(alarmActivity.hour.getText()).isEmpty())
//                alarmActivity.hour.setError("Cannot be left empty!!");
//            else if (String.valueOf(alarmActivity.minute.getText()).isEmpty())
//                alarmActivity.minute.setError("Cannot be left empty!!");
//            else if (String.valueOf(alarmActivity.second.getText()).isEmpty())
//                alarmActivity.second.setError("Cannot be left empty!!");
//            else {
//                AlertDialog.Builder builder = new AlertDialog.Builder(alarm_timer.this);
//                builder.setMessage("Are you to set the Timer?");
//
//                builder.setTitle("Confirm").setIcon(R.drawable.nityajal);
//                builder.setPositiveButton("Yes", (dialog, which) -> {
//                    setTimeHour();
////                        dayFetch();
////                        setTimeHour();
//                });
//                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//                Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                buttonPositive.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
//                Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                buttonNegative.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
//            }
//
//        });
//        alarmActivity.cancel.setOnClickListener(view -> {
//            Intent intent = new Intent(alarm_timer.this, dashboard.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//            count = null;
//        });

    }

//    private void checkCount() {
//        did = globals.getDid();
//        userId = sessionManager.getUid();
//
//        databaseReference.child("data").child(userId).child(did).child("Timer").addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists())
//                    setCount(Math.toIntExact(snapshot.getChildrenCount()));
//                else
//                    setCount(0);
//
//
////                if (count == 0) {
////                    alarmActivity.time1.setClickable(true);
////                    alarmActivity.time2.setClickable(false);
////                    alarmActivity.time3.setClickable(false);
//////                    alarmActivity.counts.setText("3");
////                } else if (count == 1) {
////                    alarmActivity.time1.setClickable(false);
////                    alarmActivity.time2.setClickable(true);
////                    alarmActivity.time3.setClickable(false);
//////                    alarmActivity.counts.setText("2");
////                } else if (count == 2) {
////                    alarmActivity.time1.setClickable(false);
////                    alarmActivity.time2.setClickable(false);
////                    alarmActivity.time3.setClickable(true);
//////                    alarmActivity.counts.setText("1");
////                } else {
////                    alarmActivity.time1.setClickable(false);
////                    alarmActivity.time2.setClickable(false);
////                    alarmActivity.time3.setClickable(false);
//////                    alarmActivity.counts.setText("0");
////                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(alarm_timer.this, dashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setTimeHour() {
//        dayFetch();
//        if (btnpress == 0) {
//            Toast.makeText(this, "Set a Timer First!!", Toast.LENGTH_SHORT).show();
//        } else {
//            int hr = Integer.parseInt(String.valueOf(alarmActivity.hour.getText()));
//            int min = Integer.parseInt(String.valueOf(alarmActivity.minute.getText()));
//            int total_sec = (hr * 3600) + (min * 60) + Integer.parseInt(String.valueOf(alarmActivity.second.getText()));
//            Map<String, Object> timeData = new HashMap<>();
//            timeData.put("start_hour", String.valueOf(mHour));
//            timeData.put("start_minute", String.valueOf(mMinute));
//            timeData.put("duration_second", total_sec);
//            timeData.put("days", daysWeak);
////            String idCount = String.valueOf(count + 1);
//
////      data -> userId (which user has which device) -> deviceID -> idCount (number of Device) : Database Fields Format
////        String userId = sessionManager.getUid();
//
//            try {
//                did = globals.getDid();
//                userId = sessionManager.getUid();
//
//                databaseReference.child("data").child(userId).child(did).child("Timer").child(String.valueOf(btnpress)).setValue(timeData).addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        setCount((count + 1));
//                        Toast.makeText(alarm_timer.this, "Success!!", Toast.LENGTH_SHORT).show();
//                        Calendar calendar = Calendar.getInstance();
//                        calendar.set(Calendar.HOUR_OF_DAY, mHour);
//                        calendar.set(Calendar.MINUTE, mMinute);
//                        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
//                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
//                        long time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
//                        if (System.currentTimeMillis() > time) {
//                            if (Calendar.AM_PM == 0) time = time + (1000 * 60 * 60 * 12);
//                            else time = time + (1000 * 60 * 60 * 24);
//                        }
//                        AlarmManager.AlarmClockInfo alinfo = new AlarmManager.AlarmClockInfo(time, pendingIntent);
//                        alarm.setAlarmClock(alinfo, pendingIntent);
//                        clearToggles();
//
//                        Intent i = new Intent(alarm_timer.this, alarm_timer.class);
//                        overridePendingTransition(0, 0);//change for your case
//                        startActivity(i);
//                    } else Toast.makeText(alarm_timer.this, "Failed!!", Toast.LENGTH_SHORT).show();
//                });
//
//            } catch (NullPointerException e) {
//                Toast.makeText(this, "Please Try Again!!", Toast.LENGTH_SHORT).show();
//            }
//
//        }

//        df.collection("data").add(timeData).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                Intent intent = new Intent(this, AlarmReceiver.class);
//                intent.putExtra("hr", hr);
//                intent.putExtra("min", min);
//                intent.putExtra("sec", sec);
//                Calendar calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY, alarmActivity.timePicker.getHour());
//                calendar.set(Calendar.MINUTE, alarmActivity.timePicker.getMinute());
//                calendar.set(Calendar.SECOND, 0);
//                calendar.set(Calendar.MILLISECOND, 0);
//
//                PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                        this.getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
//
//                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
//                        AlarmManager.INTERVAL_DAY, pendingIntent);
//
//                alarmActivity.timePicker.setVisibility(View.GONE);
//                alarmActivity.hour.setVisibility(View.GONE);
//                alarmActivity.minute.setVisibility(View.GONE);
//                alarmActivity.second.setVisibility(View.GONE);
//                alarmActivity.do1.setVisibility(View.GONE);
//                alarmActivity.do2.setVisibility(View.GONE);
//
//                Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
//
//                Intent goDash = new Intent(getApplicationContext(), dashboard.class);
//                goDash.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                startActivity(goDash);
//                String NOTIFICATION_CHANNEL_ID = "NityaJal_01";
//                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
//                notificationChannel.setDescription("Sample Channel description");
//                notificationChannel.enableLights(true);
//                notificationChannel.setLightColor(R.color.red);
//                notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//                notificationChannel.enableVibration(true);
//                notificationManager.createNotificationChannel(notificationChannel);
////                sendBroadcast(intent);
//            } else
//                Toast.makeText(alarm_timer.this, "Error!!", Toast.LENGTH_SHORT).show();
//        });
    }

//    private void clearToggles() {
//        if (tM.isChecked())
//            tM.toggle();
//        if (tT.isChecked())
//            tT.toggle();
//        if (tW.isChecked())
//            tW.toggle();
//        if (tTh.isChecked())
//            tTh.toggle();
//        if (tF.isChecked())
//            tF.toggle();
//        if (tSa.isChecked())
//            tSa.toggle();
//        if (tSu.isChecked())
//            tSu.toggle();
//        daysWeak = "";
//        alarmActivity.hour.setText("");
//        alarmActivity.minute.setText("");
//        alarmActivity.second.setText("");
//        alarmActivity.second.clearFocus();
//        btnpress = 0;
//    }

//    @SuppressLint("ResourceAsColor")
//    private void showTimer() {
//        final Calendar c = Calendar.getInstance();
//        mHour = c.get(Calendar.HOUR_OF_DAY);
//        mMinute = c.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
//                (view, hourOfDay, minute) -> {
//                    mHour = hourOfDay;
//                    mMinute = minute;
////                    timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.green(10));
//                }, mHour, mMinute, false);
//
////        timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.green(10));
////        Btnyes.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
////        Button Btnno = timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
////        Btnno.setTextColor(ContextCompat.getColor(this, R.color.dgreen));
//        timePickerDialog.show();
//        timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(this, R.color.dgreen));
//        timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(this, R.color.dgreen));
//    }

//    private void toggle_day() {
//        Calendar calendar = Calendar.getInstance();
//        int day = calendar.get(Calendar.DAY_OF_WEEK);
//        switch (day) {
//            case 1:
//                tSu.toggle();
//                break;
//            case 2:
//                tM.toggle();
//                break;
//            case 3:
//                tT.toggle();
//                break;
//            case 4:
//                tW.toggle();
//                break;
//            case 5:
//                tTh.toggle();
//                break;
//            case 6:
//                tF.toggle();
//                break;
//            case 7:
//                tSa.toggle();
//                break;
//        }
//    }

//    private void dayFetch() {
//        if (tM.isChecked())
//            daysWeak += "M, ";
//        if (tT.isChecked())
//            daysWeak += "T,";
//        if (tW.isChecked())
//            daysWeak += "W,";
//        if (tTh.isChecked())
//            daysWeak += "Th,";
//        if (tF.isChecked())
//            daysWeak += "F,";
//        if (tSa.isChecked())
//            daysWeak += "Sa,";
//        if (tSu.isChecked())
//            daysWeak += "Su, ";
//    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.time1:
//                showTimer();
//                btnpress = 1;
//                break;
//            case R.id.time2:
//                showTimer();
//                btnpress = 2;
//                break;
//            case R.id.time3:
//                showTimer();
//                btnpress = 3;
//                break;
//
//        }
//    }

}