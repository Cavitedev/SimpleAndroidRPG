package com.example.simplerpg.models;

import java.util.ArrayList;

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

    private static Party defaultParty() {
        Party party = new Party();

        ArrayList<Ability> abilities = new ArrayList<>();
        abilities.add(new Ability(null, "FireBall", "Launches a fire ball", Ability.Type.Special, 80));
        abilities.add(new Ability(null, "Big Kick", "Gives a strong Dick", Ability.Type.Physical, 100));
        AbilitiesLearned abilitiesLearned = new AbilitiesLearned(abilities);
        Stats exampleStats = new Stats(20, 10, 10, 30, 7, 680000);
        Stats exampleStats1 = new Stats(12, 30, 7, 10, 26, 680000);
        Stats exampleStats2 = new Stats(30, 7, 7, 20, 20, 680000);
        Stats exampleStats3 = new Stats(30, 7, 30, 30, 19, 680000);
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
}
