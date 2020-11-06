package com.example.simplerpg.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Party implements Parcelable {
    Hero[][] heroes = new Hero[3][2];

    public Party() {

    }

    protected Party(Parcel in) {
        Hero[] heroesArray = in.createTypedArray(Hero.CREATOR);

        this.addHeroes(heroesArray);
    }

    public Hero[][] getHeroes() {
        return heroes;
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

    public Hero getHeroAt(int x, int y) {
        return heroes[x][y];
    }

    public void putHeroAt(Hero hero, int x, int y) {
        heroes[x][y] = hero;
    }

    public static final Creator<Party> CREATOR = new Creator<Party>() {
        @Override
        public Party createFromParcel(Parcel in) {
            return new Party(in);
        }

        @Override
        public Party[] newArray(int size) {
            return new Party[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(heroes);
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
}
