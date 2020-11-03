package com.example.simplerpg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.ui.heroProfile.HeroProfileActivity;
import com.example.simplerpg.ui.partyGrid.PartyGridFormationSelectionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void goToHeroProfile() {
        Intent heroProfile = new Intent(this, HeroProfileActivity.class);
        startActivity(heroProfile);
    }

    private void goToBattle() {
        Intent heroProfile = new Intent(this, PartyGridFormationSelectionActivity.class);
        startActivity(heroProfile);
    }

    public void button(View view) {
        goToHeroProfile();
    }

    public void buttonBattle(View view) {
        goToBattle();
    }
}