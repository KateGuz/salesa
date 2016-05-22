package com.salesa.entity;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.salesa.util.mapper.CategorySerializer;
import com.salesa.util.mapper.UserSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class Advert {
    private int id;
    private String title;
    private String text;
    private LocalDateTime modificationDate;
    @JsonSerialize(using = CategorySerializer.class)
    private Category category;
    private double price;
    private String currency;
    @JsonSerialize(using = UserSerializer.class)
    private User user;
    private String status;
    private List<Image> images;

    public Advert(int id, String title, String text, LocalDateTime modificationDate, Category category, double price, String currency, User user, String status) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.modificationDate = modificationDate;
        this.category = category;
        this.price = price;
        this.currency = currency;
        this.user = user;
        this.status = status;
    }

    public Advert() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public User getUser() {
        return user;
    }

    public String getStatus() {
        return status;
    }

    public List<Image> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", modificationDate=" + modificationDate +
                ", category=" + category +
                ", price=" + price +
                ", currency='" + currency + '\'' +
                ", user=" + user +
                ", status='" + status + '\'' +
                '}';
    }
}
