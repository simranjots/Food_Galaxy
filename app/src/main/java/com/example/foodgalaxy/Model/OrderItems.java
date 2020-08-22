package com.example.foodgalaxy.Model;

public class OrderItems {
    private int Id;
    private int Order_Id;
    private int Menu_Id;
    private int quantity;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getOrder_Id() {
        return Order_Id;
    }

    public void setOrder_Id(int order_Id) {
        Order_Id = order_Id;
    }

    public int getMenu_Id() {
        return Menu_Id;
    }

    public void setMenu_Id(int menu_Id) {
        Menu_Id = menu_Id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItems(int id, int order_Id, int menu_Id, int quantity) {
        Id = id;
        Order_Id = order_Id;
        Menu_Id = menu_Id;
        this.quantity = quantity;
    }
}
