package com.example.simplerpg.domain.models;

import com.example.simplerpg.domain.IIdentifieable;

public class Ability implements IIdentifieable<Integer> {
    private Integer id;
    private String name, description;
    private Type type;

    private int power;

    public Ability(Integer id, String name, String description, Type type, int power) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.power = power;
    }

    public enum Type {
        Physical, Special, Status
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ability)) return false;

        Ability ability = (Ability) o;

        return getId() != null ? getId().equals(ability.getId()) : ability.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
