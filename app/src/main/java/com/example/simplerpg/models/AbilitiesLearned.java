package com.example.simplerpg.models;

import java.util.ArrayList;

public class AbilitiesLearned {
    ArrayList<Ability> abilities = new ArrayList();

    public AbilitiesLearned() {
    }

    public AbilitiesLearned(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

}
