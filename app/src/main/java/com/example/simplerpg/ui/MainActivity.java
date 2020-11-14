package com.example.simplerpg.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Party;
import com.example.simplerpg.ui.partyCreation.PartyCreationActivity;
import com.example.simplerpg.ui.partyGrid.PartyGridFormationSelectionActivity;

public class MainActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent partyGrid = new Intent(this, PartyGridFormationSelectionActivity.class);
        startActivity(partyGrid);
    }
}