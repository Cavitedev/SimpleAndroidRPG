package com.example.simplerpg.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {

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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        if (id == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(id);
        }

        dest.writeString(image);
        dest.writeParcelable(stats, flags);
        dest.writeParcelable(abilitiesLearned, flags);
    }

    protected Hero(Parcel in) {
        name = in.readString();
        id = in.readInt();
        image = in.readString();
        stats = in.readParcelable(stats.getClass().getClassLoader());
        abilitiesLearned = in.readParcelable(abilitiesLearned.getClass().getClassLoader());
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

    public AbilitiesLearned getAbilitiesLearned() {
        return abilitiesLearned;
    }

    @Override
    public int describeContents() {
        return 0;
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
