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
import com.example.simplerpg.models.Hero;
import com.example.simplerpg.ui.SkillsTreesActivity;
import com.example.simplerpg.ui.heroProfile.heroProperties.HeroPropertiesFragment;
import com.google.gson.Gson;

public class HeroProfileActivity extends AppCompatActivity {

    private Hero hero;
    private TextView nameTextView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_profile);

        hero = new Gson().fromJson(getIntent().getStringExtra("hero"), Hero.class);

        Fragment fragment = HeroPropertiesFragment.newInstance(hero);

        getSupportFragmentManager().beginTransaction().add(R.id.heroProfile_fragmentContainerHeroProperties, fragment).commit();


        getComponentsID();


        updateComponents(hero);
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