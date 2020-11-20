package com.example.simplerpg.domain.models;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hero)) return false;

        Hero hero = (Hero) o;

        if (getId() != null ? !getId().equals(hero.getId()) : hero.getId() != null) return false;
        if (getName() != null ? !getName().equals(hero.getName()) : hero.getName() != null)
            return false;
        if (getImage() != null ? !getImage().equals(hero.getImage()) : hero.getImage() != null)
            return false;
        if (getStats() != null ? !getStats().equals(hero.getStats()) : hero.getStats() != null)
            return false;
        return getAbilitiesLearned() != null ? getAbilitiesLearned().equals(hero.getAbilitiesLearned()) : hero.getAbilitiesLearned() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getStats() != null ? getStats().hashCode() : 0);
        result = 31 * result + (getAbilitiesLearned() != null ? getAbilitiesLearned().hashCode() : 0);
        return result;
    }
}
