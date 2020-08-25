package com.example.foodgalaxy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Orders implements Parcelable {

    private long id;
    private int r_Id;
    private long u_Id;
    private String totalPrice;
    private String status;
    private String deliveryAddress;
    private String dateOrdered;
    private String paymentState;
    private String dateOfBooking;
    private List<CartItem> foods;

    public Orders(){}

    protected Orders(Parcel in) {
        id = in.readLong();
        r_Id = in.readInt();
        u_Id = in.readLong();
        totalPrice = in.readString();
        status = in.readString();
        deliveryAddress = in.readString();
        dateOrdered = in.readString();
        paymentState = in.readString();
        dateOfBooking = in.readString();
    }

    public static final Creator<Orders> CREATOR = new Creator<Orders>() {
        @Override
        public Orders createFromParcel(Parcel in) {
            return new Orders(in);
        }

        @Override
        public Orders[] newArray(int size) {
            return new Orders[size];
        }
    };

    public String getPaymentState() {
        return paymentState;
    }

    public void setPaymentState(String paymentState) {
        this.paymentState = paymentState;
    }

    public List<CartItem> getFoods() {
        return foods;
    }

    public void setFoods(List<CartItem> foods) {
        this.foods = foods;
    }

    public String getDateOfBooking() {
        return dateOfBooking;
    }

    public void setDateOfBooking(String dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public Orders(long id, int r_Id, long u_Id, String totalPrice, String status, String deliveryAddress, String dateOrdered, String paymentState, String dateOfBooking, List<CartItem> foods) {
        this.id = id;
        this.r_Id = r_Id;
        this.u_Id = u_Id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.dateOrdered = dateOrdered;
        this.paymentState = paymentState;
        this.dateOfBooking = dateOfBooking;
        this.foods = foods;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getR_Id() {
        return r_Id;
    }

    public void setR_Id(int r_Id) {
        this.r_Id = r_Id;
    }

    public long getU_Id() {
        return u_Id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setU_Id(long u_Id) {
        this.u_Id = u_Id;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(String dateOrdered) {
        this.dateOrdered = dateOrdered;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(r_Id);
        dest.writeLong(u_Id);
        dest.writeString(totalPrice);
        dest.writeString(status);
        dest.writeString(deliveryAddress);
        dest.writeString(dateOrdered);
        dest.writeString(paymentState);
        dest.writeString(dateOfBooking);
    }
}
