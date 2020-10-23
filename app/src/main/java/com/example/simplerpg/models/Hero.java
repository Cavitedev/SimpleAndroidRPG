package com.example.simplerpg.models;

public class Hero {

    //Higher means more xp to get a hero to a lvl
    private static final double HERO_LVL_PROGRESSION_RATE = 7;

    private int id;
    private String name;
    private String image;

    private Stats stats;
    private int xp;

    public Hero(int id, String name, String image, Stats stats, int xp) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.xp = xp;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public Stats getStats() {
        return stats;
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
}
