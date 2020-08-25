package com.example.foodgalaxy.Model;

import java.util.List;

public class Orders {

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
}
