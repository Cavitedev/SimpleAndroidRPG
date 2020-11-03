package com.example.simplerpg.ui.heroProfile;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.models.AbilitiesLearned;
import com.example.simplerpg.models.Ability;
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.models.Stats;
import com.example.simplerpg.ui.SkillsTreesActivity;
import com.example.simplerpg.ui.heroProfile.heroProperties.HeroPropertiesFragment;

import java.util.ArrayList;

public class HeroProfileActivity extends AppCompatActivity {

    TextView nameTextView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        ArrayList<Ability> abilities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            abilities.add(new Ability(null, "FireBall", "Launches a fire ball", Ability.Type.Special, 80));
            abilities.add(new Ability(null, "Big Kick", "Gives a strong Dick", Ability.Type.Physical, 100));
        }

        AbilitiesLearned abilitiesLearned = new AbilitiesLearned(abilities);

        Stats exampleStats = new Stats(30, 7, 7, 30, 30, 680000);
        Hero exampleHero = new Hero(null, "Swarchenager", "rogue", exampleStats, abilitiesLearned);

        Fragment fragment = HeroPropertiesFragment.newInstance(exampleHero);

        getSupportFragmentManager().beginTransaction().add(R.id.heroProfile_fragmentContainerHeroProperties, fragment).commit();


        getComponentsID();


        updateComponents(exampleHero);
    }

    private void getComponentsID() {
        nameTextView = findViewById(R.id.heroProfile_name);
        imageView = findViewById(R.id.heroProfile_image);
    }

    private void updateComponents(Hero hero) {
        nameTextView.setText(hero.getName());

        String uri = "@drawable/" + hero.getImage();  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        imageView.setImageDrawable(res);

    }

    public void skillBookClicked(View view) {
        Intent skillTreesActivity = new Intent(this, SkillsTreesActivity.class);
        startActivity(skillTreesActivity);
    }
}