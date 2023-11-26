package com.example.watersensor;

import static com.example.watersensor.globals.databaseReference;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.watersensor.databinding.FragmentAutoSetupBinding;

public class autoSetup extends Fragment implements View.OnClickListener {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CardView min1, min5, min10, min15;

        View view = inflater.inflate(R.layout.fragment_auto_setup, container, false);

        min1 = view.findViewById(R.id.min1);
        min5 = view.findViewById(R.id.min5);
        min10 = view.findViewById(R.id.min10);
        min15 = view.findViewById(R.id.min15);

//        min1.setOnClickListener(this);
        min1.setOnClickListener(this);
        min5.setOnClickListener(this);
        min10.setOnClickListener(this);
        min15.setOnClickListener(this);
        return view;

    }

    private void showAlertDialog(int duration) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Are you to set the Timer?");
        builder.setTitle("Confirm").setIcon(R.drawable.nityajal);
        builder.setPositiveButton("Yes", (dialog, which) -> storeTime(duration));
        builder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        Button buttonPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        buttonPositive.setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
        Button buttonNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        buttonNegative.setTextColor(ContextCompat.getColor(requireActivity(), R.color.dgreen));
    }

    private void storeTime(int duration) {
        String userId = new SessionManager(requireActivity()).getUid();
        String device = globals.getDid();
        databaseReference.child("data").child(userId).child(device).child("Data").child("Autosetup").getRef().setValue(duration);
        Toast.makeText(requireActivity(), duration +" min arranged!!", Toast.LENGTH_SHORT).show();
        requireActivity().finish();
        startActivity(requireActivity().getIntent());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.min1:
                showAlertDialog(1);
                break;

            case R.id.min5:
                showAlertDialog(5);
                break;

            case R.id.min10:
                showAlertDialog(10);
                break;

            case R.id.min15:
                showAlertDialog(15);
                break;


        }

    }
}