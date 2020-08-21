package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class FoodStyle implements Parcelable {
    private int Id;
    private String Name;


    protected FoodStyle(Parcel in) {
        Id = in.readInt();
        Name = in.readString();
    }

    public static final Creator<FoodStyle> CREATOR = new Creator<FoodStyle>() {
        @Override
        public FoodStyle createFromParcel(Parcel in) {
            return new FoodStyle(in);
        }

        @Override
        public FoodStyle[] newArray(int size) {
            return new FoodStyle[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public FoodStyle(int id, String name) {
        Id = id;
        Name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(Name);
    }
}
