package com.example.simplerpg.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.AbilitiesLearned;
import com.example.simplerpg.models.Ability;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Stats;
import com.example.simplerpg.ui.heroProperties.HeroProperties;

import java.util.ArrayList;

public class HeroProfile extends AppCompatActivity {

    TextView nameTextView;
    ImageView imageImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(null, "FireBall", "Launches a fire ball", Ability.Type.special, 80));
        abilities.add(new Ability(null, "Big Dick", "Gives a strong Dick", Ability.Type.physical, 100));

        AbilitiesLearned abilitiesLearned = new AbilitiesLearned(abilities);

        Stats exampleStats = new Stats(30, 12, 7, 20, 20);
        Hero exampleHero = new Hero(null, "Swarchenagger", "rogue", exampleStats, 4856430, abilitiesLearned);

        Fragment fragment = HeroProperties.newInstance(exampleHero);

        getSupportFragmentManager().beginTransaction().add(R.id.heroProfile_fragmentContainerHeroProperties, fragment).commit();


        getComponentsID();


        updateComponents(exampleHero);
    }

    private void getComponentsID() {
        nameTextView = findViewById(R.id.heroProfile_name);
        imageImageView = findViewById(R.id.heroProfile_image);
    }

    private void updateComponents(Hero hero) {
        nameTextView.setText(hero.getName());

        String uri = "@drawable/" + hero.getImage();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageImageView.setImageDrawable(res);

    }
}