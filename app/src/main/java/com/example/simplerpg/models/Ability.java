package com.example.simplerpg.models;

public class Ability {
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
}
