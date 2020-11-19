package com.example.simplerpg.ui.battle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.data.models.Party;
import com.example.simplerpg.ui.partyGrid.PartyGridFragment;

public class BattleActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        party = Party.getParty();

        Fragment partyGridFragment = PartyGridFragment.newInstance(party, PartyGridFragment.Context.COMBAT);
        getSupportFragmentManager().beginTransaction().add(R.id.battle_frameLayoutPlayerParty, partyGridFragment).commit();
    }
}