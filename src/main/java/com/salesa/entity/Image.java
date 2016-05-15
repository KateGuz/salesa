package com.salesa.entity;


public class Image {
    private int id;
    private byte[] content;
    private String type;

    public Image(int id) {
        this.id = id;
    }

    public Image(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
