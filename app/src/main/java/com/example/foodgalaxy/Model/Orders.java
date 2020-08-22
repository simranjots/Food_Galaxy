package com.example.foodgalaxy.Model;

import java.util.Date;

public class Orders {

    private int Id;
    private int R_Id;
    private int U_Id;
    private double End_price;
    private int discount;
    private Date dateOrdered;

    public Orders(int id, int r_Id, int u_Id, double end_price, int discount, Date dateOrdered) {
        Id = id;
        R_Id = r_Id;
        U_Id = u_Id;
        End_price = end_price;
        this.discount = discount;
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

    public void setU_Id(int u_Id) {
        U_Id = u_Id;
    }

    public double getEnd_price() {
        return End_price;
    }

    public void setEnd_price(double end_price) {
        End_price = end_price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Date getDateOrdered() {
        return dateOrdered;
    }

    public void setDateOrdered(Date dateOrdered) {
        this.dateOrdered = dateOrdered;
    }
}
