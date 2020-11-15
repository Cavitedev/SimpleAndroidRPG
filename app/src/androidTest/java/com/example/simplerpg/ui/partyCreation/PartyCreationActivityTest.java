package com.example.simplerpg.ui.partyCreation;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.simplerpg.R;
import com.example.simplerpg.models.Party;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PartyCreationActivityTest {


    @Rule
    public ActivityScenarioRule<PartyCreationActivity> activityRule
            = new ActivityScenarioRule<>(PartyCreationActivity.class);



    @Before
    public void loadParty(){
        Party.createParty();
    }


    @Test
    public void loadsPartyCreations() {

        Espresso.onView(ViewMatchers.withId(R.id.textViewNumberConstitution)).
                check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

//        Espresso.onView(ViewMatchers.withId(R.id.partyCreation_HeroImage)).
//                check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}
