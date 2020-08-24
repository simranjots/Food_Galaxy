package com.example.foodgalaxy.Model;

import java.util.Date;

public class Orders {

    private int Id;
    private int R_Id;
    private int U_Id;
    private double totalPrice;
    private String status;
    private String deliveryAddress;
    private String dateOrdered;

    public Orders(int id, int r_Id, int u_Id, double end_price, String status, String deliveryAddress,  String dateOrdered) {
        Id = id;
        R_Id = r_Id;
        U_Id = u_Id;
        totalPrice = end_price;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.dateOrdered = dateOrdered;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getR_Id() {
        return R_Id;
    }

    public void setR_Id(int r_Id) {
        R_Id = r_Id;
    }

    public int getU_Id() {
        return U_Id;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setU_Id(int u_Id) {
        U_Id = u_Id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
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
