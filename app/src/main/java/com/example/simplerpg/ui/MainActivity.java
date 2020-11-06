package com.example.simplerpg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.AbilitiesLearned;
import com.example.simplerpg.models.Ability;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Party;
import com.example.simplerpg.models.Stats;
import com.example.simplerpg.ui.heroProfile.HeroProfileActivity;
import com.example.simplerpg.ui.partyGrid.PartyGridFormationSelectionActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        party = new Party();

        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(null, "FireBall", "Launches a fire ball", Ability.Type.Special, 80));
        abilities.add(new Ability(null, "Big Kick", "Gives a strong Dick", Ability.Type.Physical, 100));
        AbilitiesLearned abilitiesLearned = new AbilitiesLearned(abilities);
        Stats exampleStats = new Stats(30, 7, 7, 30, 30, 680000);
        Hero exampleHero = new Hero(null, "Tanky", "tank", exampleStats, abilitiesLearned);
        Hero exampleHero1 = new Hero(null, "Archer", "archer", exampleStats, abilitiesLearned);
        Hero exampleHero2 = new Hero(null, "Berserker", "berserker", exampleStats, abilitiesLearned);
        Hero exampleHero3 = new Hero(null, "Mague", "mague", exampleStats, abilitiesLearned);

        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(exampleHero);
        heroes.add(exampleHero1);
        heroes.add(exampleHero2);
        heroes.add(exampleHero3);

        party.addHeroes(heroes);

    }

    private void goToHeroProfile() {
        Intent heroProfile = new Intent(this, HeroProfileActivity.class);
        startActivity(heroProfile);
    }

    private void goToBattle() {
        Intent partyGrid = new Intent(this, PartyGridFormationSelectionActivity.class);

        Gson gson = new Gson();
        String partyJson = gson.toJson(party);

        partyGrid.putExtra("party", partyJson);
        startActivityForResult(partyGrid, 1);
    }

    public void button(View view) {
        goToHeroProfile();
    }

    public void buttonBattle(View view) {
        goToBattle();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Gson gson = new Gson();
        party = gson.fromJson(data.getStringExtra("party"), Party.class);
    }
}