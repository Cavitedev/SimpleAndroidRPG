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
import com.example.simplerpg.ui.listeners.DragListener;
import com.example.simplerpg.ui.listeners.TouchListener;


public class PartyGridFragment extends Fragment {

    private Party party;
    private View partyGridFragmentView;

    public PartyGridFragment() {
        // Required empty public constructor
    }

    public static PartyGridFragment newInstance(Party party) {
        PartyGridFragment fragment = new PartyGridFragment();
        Bundle args = new Bundle();
        args.putParcelable("party", party);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        party = getArguments().getParcelable("party");

        Log.i("PARTY", "PARTY AT START\n" + party.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        partyGridFragmentView = inflater.inflate(R.layout.fragment_party_grid, container, false);
        return partyGridFragmentView;
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

        TouchListener touchListener = new TouchListener();
        DragListener dragListener = new DragListener(touchListener, party);


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
                frameLayout.setOnDragListener(dragListener);

                Hero hero = party.getHeroAt(i, j);

                heroFragment.putHero(hero);

                if (hero != null) {
                    heroFragment.updateUI();
                    heroFragment.getView().setOnTouchListener(touchListener);
                }
            }
        }
    }
}