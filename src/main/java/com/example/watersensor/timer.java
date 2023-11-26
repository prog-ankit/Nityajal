package com.example.watersensor;

import static android.content.Context.ALARM_SERVICE;
import static com.example.watersensor.globals.count;
import static com.example.watersensor.globals.databaseReference;
import static com.example.watersensor.globals.setCount;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.watersensor.databinding.FragmentTimerBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class timer extends Fragment implements View.OnClickListener{

    private AlarmManager alarm;
    private ToggleButton tM;
    private ToggleButton tT;
    private ToggleButton tW;
    private ToggleButton tTh;
    private ToggleButton tF;
    private ToggleButton tSa;
    private ToggleButton tSu;
    private int mHour, mMinute, btnpress = 0;
    private String daysWeak= "";
    private SessionManager sessionManager;
    private EditText hour, minute, second;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Button timer1, timer2, timer3, saveTimer;

        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        sessionManager = new SessionManager(view.getContext());
        timer1 = view.findViewById(R.id.time1);
        timer2 = view.findViewById(R.id.time2);
        timer3 = view.findViewById(R.id.time3);
        saveTimer = view.findViewById(R.id.saveTime);
        tM = view.findViewById(R.id.tM);
        tT = view.findViewById(R.id.tT);
        tW = view.findViewById(R.id.tW);
        tTh = view.findViewById(R.id.tTh);
        tF = view.findViewById(R.id.tF);
        tSa = view.findViewById(R.id.tSa);
        tSu = view.findViewById(R.id.tSu);
        hour = view.findViewById(R.id.hour);
        minute = view.findViewById(R.id.second);
        second = view.findViewById(R.id.minute);


        timer1.setOnClickListener(this);
        timer2.setOnClickListener(this);
        timer3.setOnClickListener(this);



        alarm = (AlarmManager) requireActivity().getSystemService(ALARM_SERVICE);
        toggle_day();

        saveTimer.setOnClickListener(v -> {

            if (String.valueOf(hour.getText()).isEmpty())
                hour.setError("Cannot be left empty!!");
            else if (String.valueOf(minute.getText()).isEmpty())
                minute.setError("Cannot be left empty!!");
            else if (String.valueOf(second.getText()).isEmpty())
                second.setError("Cannot be left empty!!");
            else {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you to set the Timer?");

                builder.setTitle("Confirm").setIcon(R.drawable.nityajal);
                builder.setPositiveButton("Yes", (dialog, which) -> {
                    setTimeHour();
                });
                builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                buttonPositive.setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
                Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                buttonNegative.setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
            }

        });
        return view;

    }



    private void toggle_day() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 1:
                tSu.toggle();
                break;
            case 2:
                tM.toggle();
                break;
            case 3:
                tT.toggle();
                break;
            case 4:
                tW.toggle();
                break;
            case 5:
                tTh.toggle();
                break;
            case 6:
                tF.toggle();
                break;
            case 7:
                tSa.toggle();
                break;
        }
    }
    private void clearToggles() {
        if (tM.isChecked())
            tM.toggle();
        if (tT.isChecked())
            tT.toggle();
        if (tW.isChecked())
            tW.toggle();
        if (tTh.isChecked())
            tTh.toggle();
        if (tF.isChecked())
            tF.toggle();
        if (tSa.isChecked())
            tSa.toggle();
        if (tSu.isChecked())
            tSu.toggle();
        daysWeak = "";
        hour.setText("");
        minute.setText("");
        second.setText("");
        second.clearFocus();
        btnpress = 0;
    }

    private void showTimer() {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                (view, hourOfDay, minute) -> {
                    mHour = hourOfDay;
                    mMinute = minute;

                }, mHour, mMinute, false);

        timePickerDialog.show();
        timePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
        timePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
    }

    private void dayFetch() {
        if (tM.isChecked())
            daysWeak += "M, ";
        if (tT.isChecked())
            daysWeak += "T,";
        if (tW.isChecked())
            daysWeak += "W,";
        if (tTh.isChecked())
            daysWeak += "Th,";
        if (tF.isChecked())
            daysWeak += "F,";
        if (tSa.isChecked())
            daysWeak += "Sa,";
        if (tSu.isChecked())
            daysWeak += "Su, ";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time1:
                showTimer();
                btnpress = 1;
                break;
            case R.id.time2:
                showTimer();
                btnpress = 2;
                break;
            case R.id.time3:
                showTimer();
                btnpress = 3;
                break;

        }
    }

    private void setTimeHour() {
        dayFetch();
        if (btnpress == 0) {
            Toast.makeText(getActivity(), "Set a Timer First!!", Toast.LENGTH_SHORT).show();
        } else {
            int hr = Integer.parseInt(String.valueOf(hour.getText()));
            int min = Integer.parseInt(String.valueOf(minute.getText()));
            int total_sec = (hr * 3600) + (min * 60) + Integer.parseInt(String.valueOf(second.getText()));
            Map<String, Object> timeData = new HashMap<>();
            timeData.put("start_hour", String.valueOf(mHour));
            timeData.put("start_minute", String.valueOf(mMinute));
            timeData.put("duration_second", total_sec);
            timeData.put("days", daysWeak);
//            String idCount = String.valueOf(count + 1);

//      data -> userId (which user has which device) -> deviceID -> idCount (number of Device) : Database Fields Format
//        String userId = sessionManager.getUid();

            try {
                String did = globals.getDid();

                String userId = sessionManager.getUid();

                databaseReference.child("data").child(userId).child(did).child("Timer").child(String.valueOf(btnpress)).setValue(timeData).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(requireActivity(), "Success!!", Toast.LENGTH_SHORT).show();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(Calendar.HOUR_OF_DAY, mHour);
                        calendar.set(Calendar.MINUTE, mMinute);
                        Intent intent = new Intent(getActivity(), AlarmReceiver.class);
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(requireActivity(), 0, intent, PendingIntent.FLAG_IMMUTABLE);
                        long time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
                        if (System.currentTimeMillis() > time) {
                            if (Calendar.AM_PM == 0) time = time + (1000 * 60 * 60 * 12);
                            else time = time + (1000 * 60 * 60 * 24);
                        }
                        AlarmManager.AlarmClockInfo alinfo = new AlarmManager.AlarmClockInfo(time, pendingIntent);
                        alarm.setAlarmClock(alinfo, pendingIntent);
                        clearToggles();

                        requireActivity().finish();
                        startActivity(requireActivity().getIntent());
                    } else Toast.makeText(requireActivity(), "Failed!!", Toast.LENGTH_SHORT).show();
                });

            } catch (NullPointerException e) {
                Toast.makeText(requireActivity(), "Please Try Again!!", Toast.LENGTH_SHORT).show();
            }

        }


    }
}