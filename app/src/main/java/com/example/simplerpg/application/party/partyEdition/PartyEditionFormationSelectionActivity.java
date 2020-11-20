package com.example.simplerpg.application.party.partyEdition;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Party;
import com.example.simplerpg.application.party.partyEdition.heroProfile.heroProperties.HeroStatsFragment;

public class PartyEditionFormationSelectionActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_grid_formation_selection);

        party = Party.getParty();

        Fragment partyEditionFragment = PartyEditionFragment.newInstance(party, PartyEditionFragment.Context.FORMATION);
        getSupportFragmentManager().beginTransaction().add(R.id.partyGrid_formationSelectionFrameLayout, partyEditionFragment).commit();

        Fragment averageStatsFragment = HeroStatsFragment.newInstance(party.getAverageStats(), HeroStatsFragment.Context.PARTY_FORMATION);
        getSupportFragmentManager().beginTransaction().add(R.id.partyGrid_averageStatsFrameLayout, averageStatsFragment).commit();
    }
}