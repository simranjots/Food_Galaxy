package com.example.foodgalaxy.Model;

public class User {
    private  String Name;
    private String Password;
    private String Phone;
    private String Address;
    private String IsStaff;

    public User() {
    }

    public User(String name, String password, String phone, String address, String isStaff) {
        Name = name;
        Password = password;
        Phone = phone;
        Address = address;
        IsStaff = "false";
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

    public String getIsStaff() {
        return IsStaff;
    }

    public void setIsStaff(String isStaff) {
        IsStaff = isStaff;
    }
}
