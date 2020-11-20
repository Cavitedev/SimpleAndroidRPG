package com.example.simplerpg.application.party.partyEdition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Hero;
import com.example.simplerpg.domain.models.Party;
import com.example.simplerpg.application.party.partyEdition.listeners.HeroClickListener;
import com.example.simplerpg.application.party.partyEdition.listeners.DragListenerDisplaceHeroes;
import com.example.simplerpg.application.party.partyEdition.listeners.LongTouchListenerStartsDragging;


public class PartyEditionFragment extends Fragment {

    private Party party;

    private Context context;

    private LongTouchListenerStartsDragging longTouchListenerStartsDragging;
    private DragListenerDisplaceHeroes dragListenerDisplaceHeroes;
    private HeroClickListener heroClickListener;


    public enum Context {
        COMBAT, FORMATION;
    }

    public PartyEditionFragment() {
        // Required empty public constructor
    }

    public static PartyEditionFragment newInstance(Party party, Context context) {
        PartyEditionFragment fragment = new PartyEditionFragment();
        fragment.party = party;
        fragment.context = context;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_party_grid, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setPartyHeroesInTheirPosition();
    }
    

    private void setPartyHeroesInTheirPosition() {

        FrameLayout frameLayout = getView().findViewById(R.id.upLeft);
        HeroFragment heroFragment = (HeroFragment) getFragmentManager().findFragmentById(R.id.upLeftFragment);

        longTouchListenerStartsDragging = new LongTouchListenerStartsDragging();
        dragListenerDisplaceHeroes = new DragListenerDisplaceHeroes(party);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                switch (j) {
                    case 0:
                        switch (i) {
                            case 0:
                                frameLayout = getView().findViewById(R.id.upLeft);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.upLeftFragment);
                                break;
                            case 1:
                                frameLayout = getView().findViewById(R.id.upCenter);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.upCenterFragment);
                                break;
                            case 2:
                                frameLayout = getView().findViewById(R.id.upRight);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.upRightFragment);
                                break;
                        }
                        break;
                    case 1:
                        switch (i) {
                            case 0:
                                frameLayout = getView().findViewById(R.id.downLeft);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.downLeftFragment);
                                break;
                            case 1:
                                frameLayout = getView().findViewById(R.id.downCenter);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.downCenterFragment);
                                break;
                            case 2:
                                frameLayout = getView().findViewById(R.id.downRight);
                                heroFragment = (HeroFragment) getChildFragmentManager().findFragmentById(R.id.downRightFragment);
                                break;
                        }
                        break;
                }

                Hero hero;
                if (party != null) {
                    hero = party.getHeroAt(i, j);
                } else {
                    hero = null;
                }

                heroFragment.putHero(hero);
                heroFragment.setUIData();

                if (context == Context.FORMATION) {

                    frameLayout.setOnDragListener(dragListenerDisplaceHeroes);

                    if (hero != null) {
                        heroClickListener = new HeroClickListener(hero, this);
                        heroFragment.getView().setOnClickListener(heroClickListener);
                        heroFragment.getView().setOnLongClickListener(longTouchListenerStartsDragging);
                    }
                }
            }
        }
    }
}