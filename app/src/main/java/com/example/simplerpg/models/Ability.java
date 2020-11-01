package com.example.simplerpg.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ability implements Parcelable {
    private Integer id;
    private String name, description;
    private Type type;

    private int power;

    public Ability(Integer id, String name, String description, Type type, int power) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.power = power;
    }

    protected Ability(Parcel in) {
        id = in.readInt();
        name = in.readString();
        description = in.readString();
        power = in.readInt();
    }

    public static final Creator<Ability> CREATOR = new Creator<Ability>() {
        @Override
        public Ability createFromParcel(Parcel in) {
            return new Ability(in);
        }

        @Override
        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id != null) {
            dest.writeInt(id);
        } else {
            dest.writeInt(0);
        }
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(power);
    }

    public enum Type {
        Physical, Special, Status
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Type getType() {
        return type;
    }

    public int getPower() {
        return power;
    }
}
