package com.salesa.entity;


import java.time.LocalDateTime;

public class Feedback {
    private int id;
    private String text;
    private User authorId;
    private User userId;
    private LocalDateTime creationDate;

    public Feedback(){

    }

    public Feedback(int id){
        this.id = id;
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

    public User getAuthorId() {
        return authorId;
    }

    public void setAuthorId(User authorId) {
        this.authorId = authorId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
                ", authorId=" + authorId +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                '}';
    }
}
