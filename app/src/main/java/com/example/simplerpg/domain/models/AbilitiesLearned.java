package com.example.simplerpg.domain.models;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesLearned {
    List<Ability> abilities = new ArrayList();

    public AbilitiesLearned() {
    }

    public AbilitiesLearned(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Ability> getAbilities() {
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
