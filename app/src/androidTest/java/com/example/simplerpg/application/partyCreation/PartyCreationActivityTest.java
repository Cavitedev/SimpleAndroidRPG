package com.example.simplerpg.application.partyCreation;

import android.view.View;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simplerpg.MyMatchers;
import com.example.simplerpg.R;
import com.example.simplerpg.domain.models.AbilitiesLearned;
import com.example.simplerpg.domain.models.Hero;
import com.example.simplerpg.domain.models.Party;
import com.example.simplerpg.domain.models.Stats;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;


@RunWith(AndroidJUnit4.class)
public class PartyCreationActivityTest {

    //Needs to match with PartyCreationActivity fields
    private final int MINIMUM_POINTS = 7;
    private final int STAT_POINTS = 23;

    @Rule
    public ActivityScenarioRule<PartyCreationActivity> activityRule
            = new ActivityScenarioRule<>(PartyCreationActivity.class);


    private View decorView;

    @Before
    public void loadParty() {
        Party.createParty();


        activityRule.getScenario().onActivity(
                activity -> decorView = activity.getWindow().getDecorView()
        );
    }


    @Test
    public void loadsPartyCreations() {

        onView(withId(R.id.textViewNumberConstitution)).
                check(matches(isDisplayed()));

        onView(withId(R.id.partyCreation_HeroImage)).
                check(matches(isDisplayed()));
    }

    @Test
    public void savesHeroWithoutStats_showsErrorInToast() {


        onView(withId(R.id.editTextTextHeroName)).perform(
                ViewActions.typeText("Random Name")
        );
        Espresso.closeSoftKeyboard();

        for (int i = 0; i < STAT_POINTS - 1; i++) {
            onView(withId(R.id.partyCreation_buttonPlusStrength)).perform(
                    ViewActions.click()
            );
        }

        onView(withId(R.id.partyCreation_buttonFinish)).perform(
                ViewActions.click()
        );
        String toastText = ApplicationProvider.getApplicationContext().getString(R.string.points_remaining_toast);
        String matchText = (1 + toastText + MINIMUM_POINTS);
        onView(withText(matchText)).
                inRoot(withDecorView(not
                        (decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void reduceMinimumStat_showsErrorInToast() {

        onView(withId(R.id.partyCreation_buttonMinusStrength)).perform(
                ViewActions.click()
        );


        String toastText = ApplicationProvider.getApplicationContext().getString(R.string.minimum_stat_toast);
        String matchText = toastText + MINIMUM_POINTS;
        onView(withText(matchText)).
                inRoot(withDecorView(not
                        (decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void addMoreStatPointsThanAvailable_showsErrorInToast() {

        for (int i = 0; i < STAT_POINTS + 1; i++) {
            onView(withId(R.id.partyCreation_buttonPlusStrength)).perform(
                    ViewActions.click()
            );
        }


        String toastText = ApplicationProvider.getApplicationContext().getString(R.string.no_points_remaining_toast);
        onView(withText(toastText)).
                inRoot(withDecorView(not
                        (decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void savesHeroWithoutName_showsErrorInToast() {


        onView(withId(R.id.editTextTextHeroName)).perform(
                ViewActions.typeText("")
        );

        for (int i = 0; i < STAT_POINTS; i++) {
            onView(withId(R.id.partyCreation_buttonPlusStrength)).perform(
                    ViewActions.click()
            );
        }

        onView(withId(R.id.partyCreation_buttonFinish)).perform(
                ViewActions.click()
        );


        onView(withText(R.string.no_name_entered_toast)).
                inRoot(withDecorView(not
                        (decorView)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void selectNewImage_updatesImage() {


        onView(withId(R.id.partyCreation_HeroImage)).perform(
                ViewActions.click()
        );

        onView(MyMatchers.withIndex(withId(R.id.singleHeroImage_imageView), 0))
                .perform(ViewActions.click());


        onView(withId(R.id.partyCreation_HeroImage)).check(
                matches(isDisplayed())).check
                (matches(MyMatchers.withDrawable(R.drawable.hero0)));

    }

    @Test
    public void fill4Member_successfullySavesParty() {
        int heroes = 4;
        for (int i = 0; i < heroes; i++) {
            onView(withId(R.id.editTextTextHeroName)).perform(
                    ViewActions.typeText("Random Name" + i)
            );
            Espresso.closeSoftKeyboard();

            onView(withId(R.id.partyCreation_HeroImage)).perform(
                    ViewActions.click()
            );

            onView(MyMatchers.withIndex(withId(R.id.singleHeroImage_imageView), i))
                    .perform(ViewActions.click());


            for (int j = 0; j < STAT_POINTS; j++) {
                onView(withId(R.id.partyCreation_buttonPlusStrength)).perform(
                        ViewActions.click()
                );
            }

            onView(withId(R.id.partyCreation_buttonFinish)).perform(
                    ViewActions.click()
            );
        }





        Party newParty = new Party();
        for (int i = 0; i < heroes; i++) {
            Stats stats = new Stats(MINIMUM_POINTS + STAT_POINTS, MINIMUM_POINTS, MINIMUM_POINTS, MINIMUM_POINTS, MINIMUM_POINTS, 0);
            Hero hero = new Hero(null,"Random Name" + i,"hero"+i ,stats, new AbilitiesLearned());

            newParty.addHero(hero);

        }
        assertThat(Party.getParty(), Matchers.equalTo(newParty));
    }


}


