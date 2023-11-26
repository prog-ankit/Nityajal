package com.example.watersensor;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        @SuppressLint("WrongConstant")
        NotificationChannel notificationChannel = null;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel("ankit bose", "My Notifications", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Timer");
            notificationChannel.enableLights(true);

            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);

        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, "ankit bose");
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.nityajal)
                .setTicker("NityaJAL")
                .setContentTitle("Timer")
                .setContentText("Device has been started!!")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
//        Intent nextActivity = new Intent(context, NotificationActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, nextActivity,0);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "androidknowledge")
//                .setSmallIcon(R.drawable.ic_notify)
//                .setContentTitle("Good Morning!")
//                .setContentText("It's time to wake up")
//                .setAutoCancel(true)
//                .setDefaults(NotificationCompat.DEFAULT_ALL)
//                .setPriority(NotificationCompat.PRIORITY_HIGH);
//        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
//        notificationManagerCompat.notify(123, builder.build());
//        String action = intent.getAction();
//        Toast.makeText(context, "Hello Alarm", Toast.LENGTH_SHORT).show();
//        Toast.makeText(context, String.valueOf(action), Toast.LENGTH_SHORT).show();

//        Toast.makeText(context, String.valueOf(intent.getStringExtra("hr")), Toast.LENGTH_SHORT).show();
//        int hr = Integer.parseInt(intent.getStringExtra("hr"));
//        int min = Integer.parseInt(intent.getStringExtra("min"));
//        int sec = Integer.parseInt(intent.getStringExtra("sec"));
//        int millis = ((hr*60)*60000) + (min*60000) + (sec *1000);

//        new CountDownTimer(millis,1000){
//            @Override
//            public void onTick(long l) {
//            }
//
//            @Override
//            public void onFinish() {
//
//            }
//        };
//        int hr = Integer.parseInt(intent.getStringExtra("hr"));
//        int min = Integer.parseInt(intent.getStringExtra("hr"));
//        int sec = Integer.parseInt(intent.getStringExtra("hr"));
//        new CountDownTimer(((min*60000L)+(sec*1000L)),1000) {
//            @Override
//            public void onTick(long l) {
//
//                alarm_timer.alarmActivty.duration.setText(" ("+(l / 1000)+")");
//            }
//
//            @Override
//            public void onFinish() {
//                Toast.makeText(context, "Alarm Set", Toast.LENGTH_SHORT).show();
//            }
//        };
//        String hr = getIntent().getStringExtra("hr");

    }
}
