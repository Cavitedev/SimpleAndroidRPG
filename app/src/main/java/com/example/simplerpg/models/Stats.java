package com.example.simplerpg.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Stats implements Parcelable {
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

    protected Stats(Parcel in) {
        strength = in.readInt();
        dexterity = in.readInt();
        intelligence = in.readInt();
        constitution = in.readInt();
        speed = in.readInt();
    }

    public static final Creator<Stats> CREATOR = new Creator<Stats>() {
        @Override
        public Stats createFromParcel(Parcel in) {
            return new Stats(in);
        }

        @Override
        public Stats[] newArray(int size) {
            return new Stats[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(strength);
        dest.writeInt(dexterity);
        dest.writeInt(intelligence);
        dest.writeInt(constitution);
        dest.writeInt(speed);
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
}
