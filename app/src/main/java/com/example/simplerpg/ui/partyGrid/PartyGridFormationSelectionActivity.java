package com.example.simplerpg.ui.partyGrid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Party;
import com.google.gson.Gson;

public class PartyGridFormationSelectionActivity extends AppCompatActivity {

    private Party party;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_grid_formation_selection);

        Gson gson = new Gson();
        String partyJson = getIntent().getStringExtra("party");
        party = gson.fromJson(partyJson, Party.class);

        PartyGridFragment partyGridFragment = PartyGridFragment.newInstance(party, PartyGridFragment.Context.FORMATION);

        getSupportFragmentManager().beginTransaction().add(R.id.partyGrid_formationSelectionFrameLayout, partyGridFragment).commit();
    }

    @Override
    public void onBackPressed() {
        Intent data = new Intent();
        data.putExtra("party", new Gson().toJson(party));
        setResult(RESULT_OK, data);
        finish();
    }
}