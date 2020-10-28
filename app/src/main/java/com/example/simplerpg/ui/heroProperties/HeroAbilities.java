package com.example.simplerpg.ui.heroProperties;

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
import com.example.simplerpg.models.Ability;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.ui.adapters.AbilitiesAdapter;

public class HeroAbilities extends Fragment implements AbilitiesAdapter.OnElementListener {

    RecyclerView recyclerView;

    public HeroAbilities() {
        // Required empty public constructor
    }

    public static HeroAbilities newInstance(Bundle bundle) {
        HeroAbilities fragment = new HeroAbilities();
        fragment.setArguments(bundle);
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

        Hero hero = getArguments().getParcelable("hero");

        recyclerView = view.findViewById(R.id.heroAbilities_recyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AbilitiesAdapter abilitiesAdapter = new AbilitiesAdapter(this.getContext(), hero.getAbilitiesLearned().getAbilities(), this);

        recyclerView.setAdapter(abilitiesAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void onElementClick(int position) {
        Hero hero = getArguments().getParcelable("hero");
        Ability ability = hero.getAbilitiesLearned().getAbilities().get(position);

        Bundle bundle = new Bundle();
        bundle.putParcelable("ability", ability);
        AbilityDescriptionDialogFragment dialogFragment = AbilityDescriptionDialogFragment.newInstance(bundle);
        dialogFragment.show(getFragmentManager(), "");

    }
}