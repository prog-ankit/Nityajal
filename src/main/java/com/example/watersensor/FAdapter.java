package com.example.watersensor;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class FAdapter extends FragmentStateAdapter {

    public FAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new timer();
            case 2:
                return new autoSetup();
            case 0:
            default:
                return new on_off();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
