package com.salesa.entity;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Image image;
    private char status;
    private char type;
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

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public int getDislikeAmount() {
        return dislikeAmount;
    }

    public void setDislikeAmount(int dislikeAmount) {
        this.dislikeAmount = dislikeAmount;
    }
}
