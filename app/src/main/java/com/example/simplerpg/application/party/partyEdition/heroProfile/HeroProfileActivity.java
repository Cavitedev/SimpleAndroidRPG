package com.example.simplerpg.application.party.partyEdition.heroProfile;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.simplerpg.R;
import com.example.simplerpg.application.party.partyEdition.heroProfile.properties.HeroPropertiesFragment;
import com.example.simplerpg.application.party.partyEdition.heroProfile.skills.SkillsTreesActivity;
import com.example.simplerpg.databinding.ActivityHeroProfileBinding;
import com.example.simplerpg.domain.models.Hero;
import com.google.gson.Gson;

public class HeroProfileActivity extends AppCompatActivity {

    private Hero hero;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHeroProfileBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_hero_profile);

        hero = new Gson().fromJson(getIntent().getStringExtra(getString(R.string.hero)), Hero.class);

        Fragment fragment = HeroPropertiesFragment.newInstance(hero);
        getSupportFragmentManager().beginTransaction().add(R.id.heroProfile_fragmentContainerHeroProperties, fragment).commit();

        binding.setHeroName(hero.getName());

        getComponentsID();
        updateComponents(hero);
    }

    private void getComponentsID() {
        imageView = findViewById(R.id.heroProfile_image);
    }

    private void updateComponents(Hero hero) {
        String uri = "@drawable/" + hero.getImage();
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Resources resources = getResources();
        Drawable drawable = ResourcesCompat.getDrawable(resources, imageResource, null);

        imageView.setImageDrawable(drawable);

    }

    public void skillBookClicked(View view) {
        Intent skillTreesActivity = new Intent(this, SkillsTreesActivity.class);
        startActivity(skillTreesActivity);
    }
}