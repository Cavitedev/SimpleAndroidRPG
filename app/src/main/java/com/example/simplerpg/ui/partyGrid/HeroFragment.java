package com.example.simplerpg.ui.partyGrid;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeroFragment extends Fragment {

    private Hero hero;

    private ImageView imageView;

    public HeroFragment() {
        // Required empty public constructor
    }

    public static HeroFragment newInstance(Hero hero) {
        HeroFragment fragment = new HeroFragment();
        Bundle args = new Bundle();
        args.putParcelable("hero", hero);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            hero = getArguments().getParcelable("hero");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.heroFragment_image);
        updateUI();
    }

    public void putHero(Hero hero) {
        this.hero = hero;
    }

    public void updateUI() {
        if (hero != null) {
            String uri = "@drawable/" + hero.getImage();
            int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            imageView.setImageDrawable(res);
        }
    }
}