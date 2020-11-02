package com.example.simplerpg.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Hero implements Parcelable {

    private Integer id;
    private String name;
    private String image;

    private Stats stats;
    private AbilitiesLearned abilitiesLearned;

    public Hero(Integer id, String name, String image, Stats stats, AbilitiesLearned abilitiesLearned) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.stats = stats;
        this.abilitiesLearned = abilitiesLearned;
    }

    protected Hero(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.readString();
        stats = in.readParcelable(Stats.class.getClassLoader());
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(image);
        dest.writeTypedObject(stats, 5);
        dest.writeTypedObject(abilitiesLearned, 1);
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
