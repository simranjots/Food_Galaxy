package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Address implements Parcelable {
    private String unitNo;
    private String street;
    private String city;
    private String postalCode;
    private String country;

    public Address(){

    }

    public Address(String unitNo, String street, String city, String postalCode, String country)
    {
        this.unitNo = unitNo;
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this. country = country;
    }

    protected Address(Parcel in) {
        unitNo = in.readString();
        street = in.readString();
        city = in.readString();
        postalCode = in.readString();
        country = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        @Override
        public Address createFromParcel(Parcel in) {
            return new Address(in);
        }

        @Override
        public Address[] newArray(int size) {
            return new Address[size];
        }
    };

    public void setUnitNo(String unitNo) {
        this.unitNo = unitNo;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUnitNo() {
        return unitNo;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(unitNo);
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(postalCode);
        dest.writeString(country);
    }
}
