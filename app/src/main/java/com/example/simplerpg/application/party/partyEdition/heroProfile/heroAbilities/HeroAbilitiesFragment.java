package com.example.simplerpg.application.party.partyEdition.heroProfile.heroAbilities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simplerpg.R;
import com.example.simplerpg.adapters.AbilitiesAdapter;
import com.example.simplerpg.domain.models.AbilitiesLearned;
import com.example.simplerpg.domain.models.Ability;

public class HeroAbilitiesFragment extends Fragment implements AbilitiesAdapter.OnElementListener {

    private RecyclerView recyclerView;
    private AbilitiesLearned abilities;

    public HeroAbilitiesFragment() {
        // Required empty public constructor
    }

    public static HeroAbilitiesFragment newInstance(AbilitiesLearned abilities) {
        HeroAbilitiesFragment fragment = new HeroAbilitiesFragment();
        fragment.abilities = abilities;
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
        return inflater.inflate(R.layout.fragment_hero_abilities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (abilities != null) {

            recyclerView = view.findViewById(R.id.heroAbilities_recyclerView);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            AbilitiesAdapter abilitiesAdapter = new AbilitiesAdapter(this.getContext(), abilities.getAbilities(), this);

            recyclerView.setAdapter(abilitiesAdapter);

            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
            recyclerView.addItemDecoration(dividerItemDecoration);
        }
    }

    @Override
    public void onElementClick(int position) {

        Ability ability = abilities.getAbilities().get(position);

        AbilityDescriptionDialogFragment dialogFragment = AbilityDescriptionDialogFragment.newInstance(ability);
        assert getFragmentManager() != null;
        dialogFragment.show(getFragmentManager(), "");

    }
}