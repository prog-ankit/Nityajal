package com.example.watersensor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dashboardAdapter extends RecyclerView.Adapter<dashboardAdapter.ViewHolder> {
    List<String> status;
    List<String> deviceids;
    List<String> dnames;
    Context ctx;
    LayoutInflater inflater;
    public dashboardAdapter(Context ctx, List<String> deviceids, List<String> dnames, List<String> status) {
        this.ctx = ctx;
        this.status = status;
        this.deviceids = deviceids;
        this.dnames = dnames;
        inflater = LayoutInflater.from(ctx);
    }
    @NonNull
    @Override
    public dashboardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_dashboard_view, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull dashboardAdapter.ViewHolder holder, int position) {
        holder.devices.setText(String.valueOf(deviceids.get(position)));
//        holder.temperature.setText(temperature.get(position) + "°C");
        holder.status.setText(("Status: " + status.get(position)));
        holder.dname.setText(dnames.get(position));
        holder.cards.setOnClickListener(v -> {
            Intent intent = new Intent(ctx, alarm_timer.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            globals.setDid(String.valueOf(deviceids.get(position)));
            intent.putExtra("did", String.valueOf(deviceids.get(position)));
            ctx.startActivity(intent);
        });


//        holder.cards.setOnLongClickListener(v -> {
//            Intent intent = new Intent(ctx, editTimers.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            intent.putExtra("did", String.valueOf(deviceids.get(position)));
//            ctx.startActivity(intent);
//            return true;
//        });

//        holder.statBtn.toggle();

//        holder.statBtn.setChecked(!status.get(position).equals("ON"));
//        Log.d("Position ==>",String.valueOf(status.get(position)));
//        holder.statBtn.setOnCheckedChangeListener((buttonView, isChecked) -> {
//
//            int val;
//            Log.d("Toggle Stat ==>",String.valueOf(holder.statBtn));
//            if (isChecked) {
//                val = 0;
//                Log.d("Msg 1 ==<>", String.valueOf(val));
//            } else {
//                val = 1;
//            }
//            Map<String,Object> vals = new HashMap<>();
//            vals.put("status",val);
//            vals.put("temperature", temperature.get(position));
//            globals.databaseReference.child("data").child(userId).child(String.valueOf(deviceids.get(position))).child("Data").child("Status").getRef().setValue(val);
//
//        });

    }

//    private void setTimer(@NonNull dashboardAdapter.ViewHolder holder, int position) {
//        Long milli = Long.parseLong(times.get(position))*1000;
//        new CountDownTimer(milli, 1000) {
//            public void onTick(long millisec) {
//                holder.timing.setText(String.valueOf(millisec/1000));
//            }
//
//            public void onFinish() {
//            }
//
//        }.start();
//    }

    @Override
    public int getItemCount() {
        return deviceids.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView devices;
        TextView status;
        TextView dname;
        CardView cards;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            devices = itemView.findViewById(R.id.dev_id);
            status = itemView.findViewById(R.id.status);
            dname = itemView.findViewById(R.id.dname);

            cards = itemView.findViewById(R.id.state);
        }
    }
}
