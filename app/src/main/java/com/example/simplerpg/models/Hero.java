package com.example.simplerpg.models;

public class Hero {

    private Integer id;
    private String name;
    private String image;

    private Stats stats = new Stats();
    private AbilitiesLearned abilitiesLearned = new AbilitiesLearned();

    public Hero(Integer id, String name, String image, Stats stats, AbilitiesLearned abilitiesLearned) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.abilitiesLearned = abilitiesLearned;
    }

    public Integer getId() {
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

    public AbilitiesLearned getAbilitiesLearned() {
        return abilitiesLearned;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", stats=" + stats +
                '}';
    }
}
