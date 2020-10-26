package com.example.simplerpg.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Stats;
import com.example.simplerpg.ui.heroProperties.HeroProperties;

public class HeroProfile extends AppCompatActivity {

    TextView nameTextView;
    ImageView imageImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        Stats exampleStats = new Stats(30, 12, 7, 20, 20);
        Hero exampleHero = new Hero(0, "Swarchenagger", "rogue", exampleStats, 4856430);

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