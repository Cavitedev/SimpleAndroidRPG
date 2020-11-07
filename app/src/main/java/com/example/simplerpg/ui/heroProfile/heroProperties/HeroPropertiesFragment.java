package com.example.simplerpg.ui.heroProfile.heroProperties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.ui.adapters.HeroPropertiesPageAdapter;
import com.google.android.material.tabs.TabLayout;


public class HeroPropertiesFragment extends Fragment {
    private Hero hero;

    public HeroPropertiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param hero
     * @return A new instance of fragment HeroProperties.
     */

    public static HeroPropertiesFragment newInstance(Hero hero) {
        HeroPropertiesFragment fragment = new HeroPropertiesFragment();
        fragment.hero = hero;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero_properties, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.heroProperties_viewPager);
        viewPager.setAdapter(new HeroPropertiesPageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, hero));

        TabLayout tabLayout = view.findViewById(R.id.heroProperties_tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }
}