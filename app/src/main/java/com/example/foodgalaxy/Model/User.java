package com.example.foodgalaxy.Model;

public class User {
    private long Id;
    private String Rest_id;
    private String Name;
    private String Password;
    private String Phone;
    private String Address;
    private String IsStaff;

    public User() {
    }

    public User(long id,String name, String password, String phone, String address) {
        Id = id;
        Name = name;
        Password = password;
        Phone = phone;
        Address = address;
        IsStaff = "false";
        Rest_id = "null";
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getRest_id() {
        return Rest_id;
    }

    public void setRest_id(String rest_id) {
        Rest_id = rest_id;
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
