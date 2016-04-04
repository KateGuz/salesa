package com.salesa.entity;

import java.io.InputStream;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private InputStream avatar;
    private String status;
    private String type;
    private int dislikeAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public InputStream getAvatar() {
        return avatar;
    }

    public void setAvatar(InputStream avatar) {
        this.avatar = avatar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDislikeAmount() {
        return dislikeAmount;
    }

    public void setDislikeAmount(int dislikeAmount) {
        this.dislikeAmount = dislikeAmount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar=" + avatar +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", dislikeAmount=" + dislikeAmount +
                '}';
    }
}
