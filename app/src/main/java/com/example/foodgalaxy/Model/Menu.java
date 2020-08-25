package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
     private int id;
     private String name;
     private double price;
     private String description;
     private int r_Id;
     private boolean predefinedMenu;
     private String imageLink;

    public Menu() {
    }

    public Menu(int id, String name, double price, String description, int r_id, boolean predefinedMenu, String imageLink) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        r_Id = r_id;
        this.predefinedMenu = predefinedMenu;
        this.imageLink = imageLink;
    }

    protected Menu(Parcel in) {
        id = in.readInt();
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
        r_Id = in.readInt();
        predefinedMenu = in.readByte() != 0;
        imageLink = in.readString();
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
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return r_Id;
    }

    public void setR_Id(int r_Id) {
        this.r_Id = r_Id;
    }

    public boolean isPredefinedMenu() {
        return predefinedMenu;
    }

    public void setPredefinedMenu(boolean predefinedMenu) {
        this.predefinedMenu = predefinedMenu;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeInt(r_Id);
        dest.writeByte((byte) (predefinedMenu ? 1 : 0));
        dest.writeString(imageLink);
    }
}
