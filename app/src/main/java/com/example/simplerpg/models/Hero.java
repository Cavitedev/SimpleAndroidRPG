package com.example.simplerpg.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Hero implements Parcelable {

    //Higher means more xp to get a hero to a lvl
    private static final double HERO_LVL_PROGRESSION_RATE = 7;

    private Integer id;
    private String name;
    private String image;

    private Stats stats;
    private int xp;
    private AbilitiesLearned abilities;

    public Hero(Integer id, String name, String image, Stats stats, int xp, AbilitiesLearned abilities) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.xp = xp;
        this.abilities = abilities;
    }

    protected Hero(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        stats = in.readParcelable(Stats.class.getClassLoader());
        xp = in.readInt();
    }

    public static final Creator<Hero> CREATOR = new Creator<Hero>() {
        @Override
        public Hero createFromParcel(Parcel in) {
            return new Hero(in);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };

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

    public AbilitiesLearned getAbilities() {
        return abilities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);
        dest.writeTypedObject(stats, 5);
        dest.writeInt(xp);
        dest.writeTypedObject(abilities, 1);
    }

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", stats=" + stats +
                ", xp=" + xp +
                '}';
    }
}
