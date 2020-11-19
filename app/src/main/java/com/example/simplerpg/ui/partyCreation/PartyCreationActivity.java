package com.example.simplerpg.ui.partyCreation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.simplerpg.R;
import com.example.simplerpg.data.models.AbilitiesLearned;
import com.example.simplerpg.data.models.Hero;
import com.example.simplerpg.data.models.Party;
import com.example.simplerpg.data.models.Stats;
import com.example.simplerpg.ui.MainActivity;

import java.lang.reflect.Field;

public class PartyCreationActivity extends AppCompatActivity {

    private final int MINIMUM_POINTS = 7;
    private final int STAT_POINTS = 23;

    private ImageView heroImage;
    private String heroImageName = "hero5";
    private int pointsRemaining, strength, dexterity, intelligence, constitution, speed;
    private TextView textViewStrength, textViewDexterity, textViewIntelligence, textViewConstitution, textViewSpeed;
    private EditText editTextHeroName;
    private TextView textViewPointsRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_party_creation);

        heroImage = findViewById(R.id.partyCreation_HeroImage);
        textViewStrength = findViewById(R.id.textViewNumberStrength);
        textViewDexterity = findViewById(R.id.textViewNumberDexterity);
        textViewIntelligence = findViewById(R.id.textViewNumberIntelligence);
        textViewConstitution = findViewById(R.id.textViewNumberConstitution);
        textViewSpeed = findViewById(R.id.textViewNumberSpeed);
        textViewPointsRemaining = findViewById(R.id.textViewPointsRemaining);
        editTextHeroName = findViewById(R.id.editTextTextHeroName);

        resetUI();

        heroImage.setOnClickListener(v -> {
            heroImage.clearAnimation();
            Intent selectImage = new Intent(getApplicationContext(), HeroImageSelectionActivity.class);
            startActivityForResult(selectImage, 1);
        });
    }

    public void plusMinusClicked(View view) {
        switch (view.getId()) {
            case R.id.partyCreation_buttonPlusStrength:
                if (haveRemainingPoints()) {
                    pointsRemaining--;
                    strength++;
                    textViewStrength.setText(String.valueOf(strength));
                } else {
                    showNoPointsRemainingMessage();
                }
                break;
            case R.id.partyCreation_buttonMinusStrength:
                if (!isOnLowLimit(strength)) {
                    pointsRemaining++;
                    strength--;
                    textViewStrength.setText(String.valueOf(strength));
                } else {
                    showMinimumStatMessage();
                }
                break;
            case R.id.partyCreation_buttonPlusDexterity:
                if (haveRemainingPoints()) {
                    pointsRemaining--;
                    dexterity++;
                    textViewDexterity.setText(String.valueOf(dexterity));
                } else {
                    showNoPointsRemainingMessage();
                }
                break;
            case R.id.partyCreation_buttonMinusDexterity:
                if (!isOnLowLimit(dexterity)) {
                    pointsRemaining++;
                    dexterity--;
                    textViewDexterity.setText(String.valueOf(dexterity));
                } else {
                    showMinimumStatMessage();
                }
                break;
            case R.id.partyCreation_buttonPlusIntelligence:
                if (haveRemainingPoints()) {
                    pointsRemaining--;
                    intelligence++;
                    textViewIntelligence.setText(String.valueOf(intelligence));
                } else {
                    showNoPointsRemainingMessage();
                }
                break;
            case R.id.partyCreation_buttonMinusIntelligence:
                if (!isOnLowLimit(intelligence)) {
                    pointsRemaining++;
                    intelligence--;
                    textViewIntelligence.setText(String.valueOf(intelligence));
                } else {
                    showMinimumStatMessage();
                }
                break;
            case R.id.partyCreation_buttonPlusConstitution:
                if (haveRemainingPoints()) {
                    pointsRemaining--;
                    constitution++;
                    textViewConstitution.setText(String.valueOf(constitution));
                } else {
                    showNoPointsRemainingMessage();
                }
                break;
            case R.id.partyCreation_buttonMinusConstitution:
                if (!isOnLowLimit(constitution)) {
                    pointsRemaining++;
                    constitution--;
                    textViewConstitution.setText(String.valueOf(constitution));
                } else {
                    showMinimumStatMessage();
                }
                break;
            case R.id.partyCreation_buttonPlusSpeed:
                if (haveRemainingPoints()) {
                    pointsRemaining--;
                    speed++;
                    textViewSpeed.setText(String.valueOf(speed));
                } else {
                    showNoPointsRemainingMessage();
                }
                break;
            case R.id.partyCreation_buttonMinusSpeed:
                if (!isOnLowLimit(speed)) {
                    pointsRemaining++;
                    speed--;
                    textViewSpeed.setText(String.valueOf(speed));
                } else {
                    showMinimumStatMessage();
                }
                break;
        }
        textViewPointsRemaining.setText("Points remaining: " + pointsRemaining);
    }

    public void finishButton(View view) {
        if (pointsRemaining == 0) {
            if (!editTextHeroName.getText().toString().equals("")) {
                createHero();
                if (Party.getParty().getHeroesCount() < 4) {
                    nextHero();
                    Toast.makeText(getBaseContext(), "Hero " + Party.getParty().getHeroesCount() + " created", Toast.LENGTH_SHORT).show();
                } else {
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                    finish();
                    Toast.makeText(getBaseContext(), "Party created", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getBaseContext(), getString(R.string.no_name_entered_toast), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getBaseContext(), pointsRemaining + " " + getString(R.string.points_remaining_toast), Toast.LENGTH_SHORT).show();
        }
    }

    private void createHero() {
        AbilitiesLearned abilitiesLearned = new AbilitiesLearned();
        Stats stats = new Stats(strength, dexterity, intelligence, constitution, speed, 0);
        Party.getParty().addHero(new Hero(null, editTextHeroName.getText().toString(), heroImageName, stats, abilitiesLearned));
    }

    private void nextHero() {
        resetUI();
    }

    private void resetUI() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bigandsmaller);
        float animationScale = 1;
        try {
            animationScale = Settings.Global.getFloat(getContentResolver(), Settings.Global.ANIMATOR_DURATION_SCALE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        if(animationScale != 0){
            long newDuration = (long)(animation.getDuration() * animationScale);
            animation.setDuration(newDuration);
            animation.setRepeatCount(Animation.INFINITE);
            animation.setRepeatMode(Animation.REVERSE);
            heroImage.setDrawingCacheEnabled(true);
            heroImage.startAnimation(animation);
        }


        strength = dexterity = intelligence = constitution = speed = MINIMUM_POINTS;
        pointsRemaining = STAT_POINTS;

        textViewStrength.setText(String.valueOf(strength));
        textViewDexterity.setText(String.valueOf(dexterity));
        textViewIntelligence.setText(String.valueOf(intelligence));
        textViewConstitution.setText(String.valueOf(constitution));
        textViewSpeed.setText(String.valueOf(speed));
        textViewPointsRemaining.setText("Points remaining: " + pointsRemaining);
        editTextHeroName.setText("");
    }

    private boolean isOnLowLimit(int stat) {
        return stat <= MINIMUM_POINTS;
    }

    private boolean haveRemainingPoints() {
        return pointsRemaining > 0;
    }

    private void showMinimumStatMessage() {
        Toast.makeText(getBaseContext(), getString(R.string.minimum_stat_toast) + " " + MINIMUM_POINTS, Toast.LENGTH_SHORT).show();
    }

    private void showNoPointsRemainingMessage() {
        Toast.makeText(this, R.string.no_points_remaining_toast, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            heroImageName = data.getStringExtra("heroImage");
            Class res = R.drawable.class;
            try {
                Field field = res.getField(heroImageName);
                heroImage.setImageResource(field.getInt(null));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }
}