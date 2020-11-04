package com.example.simplerpg.ui.partyGrid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.AbilitiesLearned;
import com.example.simplerpg.models.Ability;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Party;
import com.example.simplerpg.models.Stats;

import java.util.ArrayList;

public class PartyGridFormationSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_grid_formation_selection);


        Bundle bundle = new Bundle();
        Party party = new Party();

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

        PartyGridFragment partyGridFragment = PartyGridFragment.newInstance(party);

        getSupportFragmentManager().beginTransaction().add(R.id.partyGrid_formationSelectionFrameLayout, partyGridFragment).commit();
    }
}