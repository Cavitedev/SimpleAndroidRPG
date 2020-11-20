package com.example.simplerpg.application.partyGrid;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.Hero;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeroFragment extends Fragment {

    private Hero hero;

    private ImageView imageViewHero;
    private ImageView imageViewCard;
    private RoundCornerProgressBar healthProgressBar;

    public HeroFragment() {
        // Required empty public constructor
    }

    public static HeroFragment newInstance(Hero hero) {
        HeroFragment fragment = new HeroFragment();
        fragment.hero = hero;
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
        return inflater.inflate(R.layout.fragment_hero, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageViewHero = view.findViewById(R.id.heroFragment_image);
        imageViewCard = view.findViewById(R.id.heroFragment_card);
        healthProgressBar = view.findViewById(R.id.heroFragment_healthProgressBar);
        setUIData();
    }

    public void putHero(Hero hero) {
        this.hero = hero;
    }

    public void setUIData() {
        if (hero != null && hero.getImage() != null && hero.getImage().length() != 0) {
            String uri = "@drawable/" + hero.getImage();
            int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());
            Drawable res = getResources().getDrawable(imageResource);
            imageViewHero.setImageDrawable(res);
            imageViewCard.setImageResource(R.drawable.cardblue);

            healthProgressBar.setVisibility(View.VISIBLE);
            healthProgressBar.setProgress(100);
            healthProgressBar.setBackgroundColor(Color.parseColor("#0A000000"));
        }
    }
}