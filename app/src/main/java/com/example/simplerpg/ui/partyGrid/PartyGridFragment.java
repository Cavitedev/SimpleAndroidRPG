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


public class PartyGridFragment extends Fragment {

    Party party;

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

        Log.i("PARTY", party.toString());
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

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                switch (j) {
                    case 0:
                        switch (i) {
                            case 0:
                                frameLayout = getView().findViewById(R.id.upLeft);
                                break;
                            case 1:
                                frameLayout = getView().findViewById(R.id.upCenter);
                                break;
                            case 2:
                                frameLayout = getView().findViewById(R.id.upRight);
                                break;
                        }
                        break;
                    case 1:
                        switch (i) {
                            case 0:
                                frameLayout = getView().findViewById(R.id.downLeft);
                                break;
                            case 1:
                                frameLayout = getView().findViewById(R.id.downCenter);
                                break;
                            case 2:
                                frameLayout = getView().findViewById(R.id.downRight);
                                break;
                        }
                        break;
                }
                Hero hero = party.getHeroAt(i, j);
                Fragment heroFragment;
                if (hero != null) {
                    heroFragment = HeroFragment.newInstance(hero);
                } else {
                    heroFragment = HeroFragment.newInstance(null);
                }
                assert getFragmentManager() != null;
                getFragmentManager().beginTransaction().add(frameLayout.getId(), heroFragment).commit();

            }
        }
    }
}