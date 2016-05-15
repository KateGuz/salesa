package com.salesa.entity;


import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.time.LocalDateTime;
@JsonAutoDetect
public class Feedback {
    private int id;
    private String text;
    private User author;
    private User user;
    private LocalDateTime creationDate;

    public Feedback(int id, String text, User author, User user, LocalDateTime creationDate) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.user = user;
        this.creationDate = creationDate;
    }

    public Feedback() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", user=" + user +
                ", creationDate=" + creationDate +
                '}';
    }
}
