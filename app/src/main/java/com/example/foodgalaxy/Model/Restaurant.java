package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private int Id;
    private String name;
    private String address;
    private int cMinSize;
    private int cMaxSize;
    private int FS_Id;
    private boolean IsDelivery;
    private String imageLink;



    public Restaurant(int Id,String name, String address, int cMinSize, int cMaxSize, int FS_Id, boolean isDelivery, String imageLink) {
        this.Id = Id;
        this.name = name;
        this.address = address;
        this.cMinSize = cMinSize;
        this.cMaxSize = cMaxSize;
        this.FS_Id = FS_Id;
        IsDelivery = isDelivery;
        this.imageLink = imageLink;
    }

    protected Restaurant(Parcel in) {
        Id  = in.readInt();
        name = in.readString();
        address = in.readString();
        cMinSize = in.readInt();
        cMaxSize = in.readInt();
        FS_Id = in.readInt();
        IsDelivery = in.readByte() != 0;
        imageLink = in.readString();
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getcMinSize() {
        return cMinSize;
    }

    public void setcMinSize(int cMinSize) {
        this.cMinSize = cMinSize;
    }

    public int getcMaxSize() {
        return cMaxSize;
    }

    public void setcMaxSize(int cMaxSize) {
        this.cMaxSize = cMaxSize;
    }

    public int getFS_Id() {
        return FS_Id;
    }

    public void setFS_Id(int FS_Id) {
        this.FS_Id = FS_Id;
    }

    public boolean isDelivery() {
        return IsDelivery;
    }

    public void setDelivery(boolean delivery) {
        IsDelivery = delivery;
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
        dest.writeInt(Id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(cMinSize);
        dest.writeInt(cMaxSize);
        dest.writeInt(FS_Id);
        dest.writeByte((byte) (IsDelivery ? 1 : 0));
        dest.writeString(imageLink);
    }
}
