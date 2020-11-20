package com.example.simplerpg.domain.models;

import java.util.ArrayList;
import java.util.Arrays;

public class Party {
    private static Party party = null;



    Hero[][] heroes = new Hero[3][2];

    public Party() {

    }

    public static Party getParty() {
        return party;
    }

    public static void createParty() {
        party = new Party();
    }

    public Hero[][] getHeroes() {
        return heroes;
    }

    public int getHeroesCount() {
        int heroesCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.heroes[i][j] != null) {
                    heroesCount++;
                }
            }
        }
        return heroesCount;
    }

    public void addHeroes(ArrayList<Hero> heroes) {
        int heroN = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++, heroN++) {
                if (heroN >= heroes.size()) {
                    return;
                }
                this.heroes[i][j] = heroes.get(heroN);
            }
        }
    }

    public void addHeroes(Hero[] heroes) {
        int heroN = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++, heroN++) {
                if (heroN >= heroes.length) {
                    return;
                }
                this.heroes[i][j] = heroes[heroN];
            }
        }
    }

    public void addHero(Hero hero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.heroes[i][j] == null) {
                    this.heroes[i][j] = hero;
                    return;
                }

            }
        }
    }


    public Hero getHeroAt(int x, int y) {
        return heroes[x][y];
    }

    public void putHeroAt(Hero hero, int x, int y) {
        heroes[x][y] = hero;
    }

    public void switchHeros(int[] firstPosition, int[] secondPosition) {
        switchHeros(firstPosition[0], firstPosition[1], secondPosition[0], secondPosition[1]);
    }

    public void switchHeros(int x1, int y1, int x2, int y2) {
        Hero hero1 = getHeroAt(x1, y1);
        Hero hero2 = getHeroAt(x2, y2);
        putHeroAt(hero1, x2, y2);
        putHeroAt(hero2, x1, y1);
    }

    @Override
    public String toString() {
        StringBuilder party = new StringBuilder();
        party.append("PARTY:\n");
        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < 3; i++) {
                party.append("Hero at: x:" + i + " y:" + j + " :");
                if (heroes[i][j] == null) {
                    party.append("null");
                } else {
                    party.append(heroes[i][j].getName());
                }
                party.append("   ");
            }
            party.append("\n");
        }

        return party.toString();
    }

    public static void setDefaultParty() {
        party = defaultParty();
    }

    public static Party defaultParty() {
        Party party = new Party();

        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(null, "FireBall", "Launches a fire ball", Ability.Type.Special, 80));
        abilities.add(new Ability(null, "Big Kick", "Gives a strong Kick", Ability.Type.Physical, 100));
        AbilitiesLearned abilitiesLearned = new AbilitiesLearned(abilities);
        Stats exampleStats = new Stats(20, 10, 10, 30, 7, 0);
        Stats exampleStats1 = new Stats(12, 30, 7, 10, 26, 0);
        Stats exampleStats2 = new Stats(30, 7, 7, 20, 20, 0);
        Stats exampleStats3 = new Stats(30, 7, 30, 30, 19, 0);
        Hero exampleHero = new Hero(null, "Tanky", "hero4", exampleStats, abilitiesLearned);
        Hero exampleHero1 = new Hero(null, "Archer", "hero9", exampleStats1, abilitiesLearned);
        Hero exampleHero2 = new Hero(null, "Berserker", "hero15", exampleStats2, abilitiesLearned);
        Hero exampleHero3 = new Hero(null, "Mague", "hero6", exampleStats3, abilitiesLearned);

        ArrayList<Hero> heroes = new ArrayList<>();
        heroes.add(exampleHero);
        heroes.add(exampleHero1);
        heroes.add(exampleHero2);
        heroes.add(exampleHero3);

        party.addHeroes(heroes);
        return party;
    }

    public Stats getAverageStats() {
        ArrayList<Stats> heroesStats = new ArrayList<>();
        int heroesCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 2; j++) {
                if (this.heroes[i][j] != null) {
                    heroesCount++;
                    heroesStats.add(heroes[i][j].getStats());
                }
            }
        }

        int[] averageStats = new int[5];
        int[] sumStats = new int[5];
        for (Stats stats : heroesStats) {
            sumStats[0] += stats.getStrength();
            sumStats[1] += stats.getDexterity();
            sumStats[2] += stats.getIntelligence();
            sumStats[3] += stats.getConstitution();
            sumStats[4] += stats.getSpeed();
        }

        averageStats[0] = sumStats[0] / heroesCount;
        averageStats[1] = sumStats[1] / heroesCount;
        averageStats[2] = sumStats[2] / heroesCount;
        averageStats[3] = sumStats[3] / heroesCount;
        averageStats[4] = sumStats[4] / heroesCount;

        return new Stats(
                averageStats[0], averageStats[1],
                averageStats[2], averageStats[3],
                averageStats[4], 0);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Party party = (Party) o;

        return Arrays.deepEquals(heroes, party.heroes);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(heroes);
    }
}
