package com.example.simplerpg.data.models;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbilitiesLearned)) return false;

        AbilitiesLearned that = (AbilitiesLearned) o;

        return getAbilities() != null ? getAbilities().equals(that.getAbilities()) : that.getAbilities() == null;
    }

    @Override
    public int hashCode() {
        return getAbilities() != null ? getAbilities().hashCode() : 0;
    }
}
