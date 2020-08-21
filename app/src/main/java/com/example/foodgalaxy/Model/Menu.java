package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
     private int Id;
     private String name;
     private double price;
     private String description;
     private int R_Id;
     private boolean isPackage;

    protected Menu(Parcel in) {
        Id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
        R_Id = in.readInt();
        isPackage = in.readByte() != 0;
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getR_Id() {
        return R_Id;
    }

    public void setR_Id(int r_Id) {
        R_Id = r_Id;
    }

    public boolean isPackage() {
        return isPackage;
    }

    public void setPackage(boolean aPackage) {
        isPackage = aPackage;
    }

    public Menu(int id, String name, double price, String description, int r_Id, boolean isPackage) {
        Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        R_Id = r_Id;
        this.isPackage = isPackage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeInt(R_Id);
        dest.writeByte((byte) (isPackage ? 1 : 0));
    }
}
