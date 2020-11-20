package com.example.simplerpg.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.simplerpg.domain.models.Hero;
import com.example.simplerpg.application.heroProfile.heroProperties.HeroAbilitiesFragment;
import com.example.simplerpg.application.heroProfile.heroProperties.HeroStatsFragment;

public class HeroPropertiesPageAdapter extends FragmentPagerAdapter {
    private Hero hero;

    public HeroPropertiesPageAdapter(@NonNull FragmentManager fm, int behavior, Hero hero) {
        super(fm, behavior);
        this.hero = hero;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return HeroStatsFragment.newInstance(hero != null ? hero.getStats() : null, HeroStatsFragment.Context.HERO_PROFILE);
            case 1:
                return HeroAbilitiesFragment.newInstance(hero != null ? hero.getAbilitiesLearned() : null);
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
