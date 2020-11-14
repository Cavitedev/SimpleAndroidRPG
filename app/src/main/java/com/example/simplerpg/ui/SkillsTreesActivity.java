package com.example.simplerpg.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.simplerpg.R;
import com.example.simplerpg.adapters.AbilityTreePageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SkillsTreesActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    AbilityTreePageAdapter abilityTreePageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skills_trees);
        bottomNavigationView = findViewById(R.id.skillsTrees_bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.skillsTrees_viewPager);
        abilityTreePageAdapter = new AbilityTreePageAdapter(getSupportFragmentManager(), 1, new Bundle());
        viewPager.setAdapter(abilityTreePageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.page_dps).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.page_tank).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.page_support).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(1);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.page_dps:
                viewPager.setCurrentItem(0);
                break;
            case R.id.page_tank:
                viewPager.setCurrentItem(1);
                break;
            case R.id.page_support:
                viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }
}