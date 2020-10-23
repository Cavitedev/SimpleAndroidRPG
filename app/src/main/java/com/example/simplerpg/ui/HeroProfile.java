package com.example.simplerpg.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Stats;

public class HeroProfile extends AppCompatActivity {

    TextView nameTextView, strengthTextView, dexterityTextView, intelligenceTextView, constitutionTextView, speedTextView, lvlTextView, xpTillNextLvlTextView;
    ImageView imageImageView;
    ProgressBar lvlProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        getComponentsID();

        Stats exampleStats = new Stats(20, 10, 7, 13, 16);
        Hero exampleHero = new Hero(0, "Manin", "strong_attacks", exampleStats, 5000);

        updateComponents(exampleHero);
    }

    private void getComponentsID() {
        nameTextView = findViewById(R.id.heroProfile_name);

        strengthTextView = findViewById(R.id.heroProfile_strengh);
        dexterityTextView = findViewById(R.id.heroProfile_dexterity);
        intelligenceTextView = findViewById(R.id.heroProfile_intelligence);
        constitutionTextView = findViewById(R.id.heroProfile_constitution);
        speedTextView = findViewById(R.id.heroProfile_speed);

        imageImageView = findViewById(R.id.heroProfile_image);

        lvlProgressBar = findViewById(R.id.heroProfile_lvlProgressBar);
        lvlTextView = findViewById(R.id.heroProfile_lvl);
        xpTillNextLvlTextView = findViewById(R.id.heroProfile_lvlXpTillNextLevel);
    }

    private void updateComponents(Hero hero) {
        nameTextView.setText(hero.getName());

        strengthTextView.setText(String.valueOf(hero.getStats().getStrength()));
        dexterityTextView.setText(String.valueOf(hero.getStats().getDexterity()));
        intelligenceTextView.setText(String.valueOf(hero.getStats().getIntelligence()));
        constitutionTextView.setText(String.valueOf(hero.getStats().getConstitution()));
        speedTextView.setText(String.valueOf(hero.getStats().getSpeed()));


        String uri = "@drawable/" + hero.getImage();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageImageView.setImageDrawable(res);

        lvlTextView.setText(String.valueOf(hero.getLvl()));
        xpTillNextLvlTextView.setText(String.valueOf(hero.getExpTillNextLvl()));

        lvlProgressBar.setProgress((int) (hero.getXpPercentageTillCurrentLvl()));
    }
}