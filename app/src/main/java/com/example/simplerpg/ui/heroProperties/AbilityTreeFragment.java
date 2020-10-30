package com.example.simplerpg.ui.heroProperties;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AbilityTreeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AbilityTreeFragment extends Fragment {

    public AbilityTreeFragment() {
        // Required empty public constructor
    }
    
    public static AbilityTreeFragment newInstance(Bundle bundle) {
        AbilityTreeFragment fragment = new AbilityTreeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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
        return inflater.inflate(R.layout.fragment_ability_tree, container, false);
    }
}