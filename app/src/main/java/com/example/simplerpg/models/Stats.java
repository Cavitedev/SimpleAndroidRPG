package com.example.simplerpg.models;

public class Stats {
    private int strength, dexterity, intelligence, constitution, speed;

    public Stats() {
        this.strength = 1;
        this.dexterity = 1;
        this.intelligence = 1;
        this.constitution = 1;
        this.speed = 1;
    }

    public Stats(int strength, int dexterity, int intelligence, int constitution, int speed) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.constitution = constitution;
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getSpeed() {
        return speed;
    }
}
