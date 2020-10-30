package com.example.simplerpg.ui.heroProperties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.simplerpg.R;
import com.example.simplerpg.ui.adapters.AbilityTreePageAdapter;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;


public class AbilityTreeDialogFragment extends DialogFragment {

    ViewPager viewPager;
    SpringDotsIndicator dotsIndicator;

    public AbilityTreeDialogFragment() {

    }

    public static AbilityTreeDialogFragment newInstance(Bundle bundle) {
        AbilityTreeDialogFragment fragment = new AbilityTreeDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return createDialogAbilityTree(inflater, container);
    }

    private View createDialogAbilityTree(LayoutInflater inflater, ViewGroup container) {

        View view = inflater.inflate(R.layout.fragment_ability_tree_dialog, container, false);

        viewPager = view.findViewById(R.id.skillTree_viewPager);
        dotsIndicator = view.findViewById(R.id.dots_indicator);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AbilityTreePageAdapter adapter = new AbilityTreePageAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, getArguments());
        viewPager.setAdapter(adapter);

        dotsIndicator.setViewPager(viewPager);

    }
}