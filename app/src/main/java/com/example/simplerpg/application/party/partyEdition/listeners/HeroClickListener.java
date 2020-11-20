package com.example.simplerpg.application.party.partyEdition.listeners;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.application.party.partyEdition.heroProfile.HeroProfileActivity;
import com.example.simplerpg.domain.models.Hero;
import com.google.gson.Gson;

public class HeroClickListener implements View.OnClickListener {

    private Hero hero;
    private Fragment fragmentContext;

    public HeroClickListener(Hero hero, Fragment fragmentContext) {
        this.hero = hero;
        this.fragmentContext = fragmentContext;

    }

    @Override
    public void onClick(View v) {

        Intent heroProfile = new Intent(fragmentContext.getActivity(), HeroProfileActivity.class);
        heroProfile.putExtra(fragmentContext.getString(R.string.hero), new Gson().toJson(hero));
        fragmentContext.startActivity(heroProfile);
    }


}
