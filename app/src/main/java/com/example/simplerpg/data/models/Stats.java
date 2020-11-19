package com.example.simplerpg.data.models;

public class Stats {

    //Higher means more xp to get a hero to a lvl
    private static final double HERO_LVL_PROGRESSION_RATE = 70;
    private static final double HEALTH_SCALE_RATIO = 7;

    private Integer id;

    private int strength, dexterity, intelligence, constitution, speed;

    private int xp;
    private int maxHealth;
    private int currentHealth;

    public Stats() {
        this.strength = 7;
        this.dexterity = 7;
        this.intelligence = 7;
        this.constitution = 7;
        this.speed = 7;
        calculateSecondaryStats();
    }

    public Stats(int strength, int dexterity, int intelligence, int constitution, int speed, int xp) {
        this.strength = strength;
        this.dexterity = dexterity;
        this.intelligence = intelligence;
        this.constitution = constitution;
        this.speed = speed;
        this.xp = xp;
        calculateSecondaryStats();
    }

    private void calculateSecondaryStats() {
        maxHealth = (int) (getLvl() * constitution * HEALTH_SCALE_RATIO);
        currentHealth = maxHealth;
    }

    public Integer getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getXp() {
        return xp;
    }

    public int getLvl() {
        return (int) Math.sqrt(xp / HERO_LVL_PROGRESSION_RATE) + 1;
    }

    public double getXpPercentageTillCurrentLvl() {
        int nextLvl = (int) Math.sqrt(xp / HERO_LVL_PROGRESSION_RATE) + 1;
        int xpNextLvl = (int) (HERO_LVL_PROGRESSION_RATE * Math.pow(nextLvl, 2));
        int xpCurrentLvl = (int) (HERO_LVL_PROGRESSION_RATE * Math.pow(nextLvl - 1, 2));

        double percentage = (((xp - xpCurrentLvl)) / ((xpNextLvl - xpCurrentLvl + 0.0))) * 100;
        return percentage;
    }


    public int getExpTillNextLvl() {
        int nextLvl = (int) Math.sqrt(xp / HERO_LVL_PROGRESSION_RATE) + 1;
        int xpNextLvl = (int) (HERO_LVL_PROGRESSION_RATE * Math.pow(nextLvl, 2));

        return xpNextLvl - xp;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    @Override
    public String toString() {
        return "Stats{" +
                "strength=" + strength +
                ", dexterity=" + dexterity +
                ", intelligence=" + intelligence +
                ", constitution=" + constitution +
                ", speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stats)) return false;

        Stats stats = (Stats) o;

        if (getStrength() != stats.getStrength()) return false;
        if (getDexterity() != stats.getDexterity()) return false;
        if (getIntelligence() != stats.getIntelligence()) return false;
        if (getConstitution() != stats.getConstitution()) return false;
        if (getSpeed() != stats.getSpeed()) return false;
        if (getXp() != stats.getXp()) return false;
        if (getMaxHealth() != stats.getMaxHealth()) return false;
        if (getCurrentHealth() != stats.getCurrentHealth()) return false;
        return getId() != null ? getId().equals(stats.getId()) : stats.getId() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getStrength();
        result = 31 * result + getDexterity();
        result = 31 * result + getIntelligence();
        result = 31 * result + getConstitution();
        result = 31 * result + getSpeed();
        result = 31 * result + getXp();
        result = 31 * result + getMaxHealth();
        result = 31 * result + getCurrentHealth();
        return result;
    }
}
