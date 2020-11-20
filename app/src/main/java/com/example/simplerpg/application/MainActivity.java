package com.example.simplerpg.application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Party;
import com.example.simplerpg.application.battle.BattleActivity;
import com.example.simplerpg.application.party.partyCreation.PartyCreationActivity;
import com.example.simplerpg.application.party.partyEdition.PartyEditionFormationSelectionActivity;

public class MainActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Party.setDefaultParty();
        if (Party.getParty() != null) {
            party = Party.getParty();
        } else {
            Party.createParty();
            Intent partyCreation = new Intent(this, PartyCreationActivity.class);
            startActivity(partyCreation);
            finish();
        }

    }

    public void buttonGoToPartyFormation(View view) {
        Intent partyGrid = new Intent(this, PartyEditionFormationSelectionActivity.class);
        startActivity(partyGrid);
    }

    public void buttonGoToBattle(View view) {
        Intent partyGrid = new Intent(this, BattleActivity.class);
        startActivity(partyGrid);
    }
}