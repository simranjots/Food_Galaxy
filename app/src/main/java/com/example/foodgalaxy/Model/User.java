package com.example.foodgalaxy.Model;

public class User {
    private long id;
    private String rest_id;
    private String Name;
    private String Password;
    private String Phone;
    private String Address;
    private boolean isStaff;

    public User() {
    }

    public User(long id, String name, String password, String phone, String address) {
        this.id = id;
        this.rest_id = "";
        Name = name;
        Password = password;
        Phone = phone;
        Address = address;
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
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public boolean getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(boolean isStaff) {
        this.isStaff = isStaff;
    }
}
