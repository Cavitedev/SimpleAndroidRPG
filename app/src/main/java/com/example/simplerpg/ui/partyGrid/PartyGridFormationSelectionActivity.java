package com.example.simplerpg.ui.partyGrid;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Party;

public class PartyGridFormationSelectionActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_grid_formation_selection);

        party = Party.getParty();

        PartyGridFragment partyGridFragment = PartyGridFragment.newInstance(party, PartyGridFragment.Context.FORMATION);

        getSupportFragmentManager().beginTransaction().add(R.id.partyGrid_formationSelectionFrameLayout, partyGridFragment).commit();
    }
}