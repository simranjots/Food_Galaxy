package com.example.foodgalaxy.Model;

public class CartItem {
    private String MenuId;
    private String name;
    private String price;
    private String quantity;
    private String spicy;
    private String comment;

    public CartItem(String menuId, String name, String price, String quantity, String spicy, String comment) {
        MenuId = menuId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.spicy = spicy;
        this.comment = comment;
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

    public String getSpicy() {
        return spicy;
    }

    public void setSpicy(String spicy) {
        this.spicy = spicy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
