package com.example.foodgalaxy.Model;

public class User {
    private long id;
    private String rest_id;
    private String name;
    private String password;
    private String phone;
    private String address;
    private boolean isStaff;

    public User() {
    }

    public User(long id, String name, String password, String phone, String address) {
        this.id = id;
        this.rest_id = "";
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.isStaff = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRest_id() {
        return rest_id;
    }

    public void setRest_id(String rest_id) {
        this.rest_id = rest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }
}
