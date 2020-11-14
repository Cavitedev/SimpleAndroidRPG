package com.example.simplerpg.adapters;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.simplerpg.ui.heroProfile.heroProperties.AbilityTreeFragment;

public class AbilityTreePageAdapter extends FragmentPagerAdapter {

    Bundle bundle;

    public AbilityTreePageAdapter(@NonNull FragmentManager fm, int behavior, Bundle savedInstanceState) {
        super(fm, behavior);
        bundle = savedInstanceState;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return AbilityTreeFragment.newInstance(new Bundle());
    }

    @Override
    public int getCount() {
        return 3;
    }

}
