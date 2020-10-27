package com.example.simplerpg.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class AbilitiesLearned implements Parcelable {
    ArrayList<Ability> abilities;

    public AbilitiesLearned(ArrayList<Ability> abilities) {
        this.abilities = abilities;
    }

    protected AbilitiesLearned(Parcel in) {
        in.readList(abilities, Ability.class.getClassLoader());
    }

    public static final Creator<AbilitiesLearned> CREATOR = new Creator<AbilitiesLearned>() {
        @Override
        public AbilitiesLearned createFromParcel(Parcel in) {
            return new AbilitiesLearned(in);
        }

        @Override
        public AbilitiesLearned[] newArray(int size) {
            return new AbilitiesLearned[size];
        }
    };

    public ArrayList<Ability> getAbilities() {
        return abilities;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(abilities);
    }
}
