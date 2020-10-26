package com.example.simplerpg.ui.heroProperties;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class HeroPropertiesPageAdapter extends FragmentPagerAdapter {
    Bundle bundle;

    public HeroPropertiesPageAdapter(@NonNull FragmentManager fm, int behavior, Bundle savedInstanceState) {
        super(fm, behavior);
        bundle = savedInstanceState;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return HeroStats.newInstance(bundle);
            case 1:
                return new HeroAbilities();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //TODO not hardcoded strings
        switch (position) {
            case 0:
                return "Stats";
            case 1:
                return "Abilities";
        }
        return null;
    }
}