package com.example.simplerpg.ui.partyGrid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Party;
import com.example.simplerpg.ui.listeners.ClickListener;
import com.example.simplerpg.ui.listeners.DragListener;
import com.example.simplerpg.ui.listeners.LongTouchListener;


public class PartyGridFragment extends Fragment implements ClickListener.OnElementListener {

    private Party party;

    private Context context;

    private LongTouchListener longTouchListener;
    private DragListener dragListener;
    private ClickListener clickListener;


    public enum Context {
        COMBAT, FORMATION;
    }

    public PartyGridFragment() {
        // Required empty public constructor
    }

    public static PartyGridFragment newInstance(Party party, Context context) {
        PartyGridFragment fragment = new PartyGridFragment();
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

    public void updateUI() {

    }

    private void setPartyHeroesInTheirPosition() {

        FrameLayout frameLayout = getView().findViewById(R.id.upLeft);
        HeroFragment heroFragment = (HeroFragment) getFragmentManager().findFragmentById(R.id.upLeftFragment);

        longTouchListener = new LongTouchListener();
        dragListener = new DragListener(longTouchListener, party);

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

                Hero hero = party.getHeroAt(i, j);

                heroFragment.putHero(hero);

                if (context == Context.FORMATION) {

                    frameLayout.setOnDragListener(dragListener);

                    if (hero != null) {
                        clickListener = new ClickListener(hero, this);
                        heroFragment.getView().setOnClickListener(clickListener);
                        heroFragment.setUIData();
                        heroFragment.getView().setOnLongClickListener(longTouchListener);
                    }
                }
            }
        }
    }

    @Override
    public void onElementClick(Hero hero) {
        Log.i("HEROCLICKED", hero.toString());
    }

}