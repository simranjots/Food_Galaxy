package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private int id;
    private String name;
    private String address;
    private int cMinsize;
    private int cMaxsize;
    private int Fs_id;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private boolean delivery;
    private String imageLink;

    public Restaurant() {
    }

    public Restaurant(int Id, String name, String address, int cMinSize, int cMaxSize, int FS_Id, boolean isDelivery, String imageLink, int u_id) {
        this.id = Id;
        this.name = name;
        this.address = address;
        this.cMinsize = cMinSize;
        this.cMaxsize = cMaxSize;
        this.Fs_id = FS_Id;
        this.delivery = isDelivery;
        this.imageLink = imageLink;
        this.uid = u_id;
    }


    protected Restaurant(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        cMinsize = in.readInt();
        cMaxsize = in.readInt();
        Fs_id = in.readInt();
        delivery = in.readByte() != 0;
        imageLink = in.readString();
        uid = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeInt(cMinsize);
        dest.writeInt(cMaxsize);
        dest.writeInt(Fs_id);
        dest.writeByte((byte) (delivery ? 1 : 0));
        dest.writeString(imageLink);
        dest.writeInt(uid);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getcMinsize() {
        return cMinsize;
    }

    public void setcMinsize(int cMinsize) {
        this.cMinsize = cMinsize;
    }

    public int getcMaxsize() {
        return cMaxsize;
    }

    public void setcMaxsize(int cMaxsize) {
        this.cMaxsize = cMaxsize;
    }

    public int getFs_id() {
        return Fs_id;
    }

    public void setFs_id(int fs_id) {
        this.Fs_id = fs_id;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }





}
