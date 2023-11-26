package com.example.watersensor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.example.watersensor.databinding.FragmentOnOffBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class on_off extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_on_off,container,false);

        SessionManager session = new SessionManager(requireActivity());
        String userId = session.getUid();
        String did = globals.getDid();
        ToggleButton toggle = view.findViewById(R.id.dstatbtn);
        globals.databaseReference.child("data").child(userId).child(did).child("Data").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                toggle.setChecked(String.valueOf(snapshot.child("Status").getValue()).equals("0"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        toggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            int val;
            if (isChecked) val = 0;
            else val = 1;
            globals.databaseReference.child("data").child(userId).child(did).child("Data").child("Status").getRef().setValue(val);
        });
        return view;
    }

}