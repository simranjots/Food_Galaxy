package com.example.foodgalaxy.Model;

public class CartItem {
    private String MenuId;
    private String name;
    private String price;
    private String quantity;

    public CartItem(String menuId, String name, String price, String quantity) {
        MenuId = menuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;

    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
