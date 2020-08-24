package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
     private String Id;
     private String name;
     private double price;
     private String description;
     private int R_Id;
     private boolean isPackage;
     private String imageLink;

    public Menu() {
    }

    protected Menu(Parcel in) {
        Id = in.readString();
        name = in.readString();
        price = in.readDouble();
        description = in.readString();
        R_Id = in.readInt();
        isPackage = in.readByte() != 0;
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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
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

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }


    public Menu(String id, String name, double price, String description, int r_Id, boolean isPackage, String imageLink) {
        this.Id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.R_Id = r_Id;
        this.isPackage = isPackage;
        this.imageLink = imageLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(name);
        dest.writeDouble(price);
        dest.writeString(description);
        dest.writeInt(R_Id);
        dest.writeByte((byte) (isPackage ? 1 : 0));
        dest.writeString(imageLink);
    }
}
