package com.salesa.entity;

import java.io.InputStream;
import java.util.Arrays;

public class Report {
    private int id;
    private String name;
    private byte [] document;

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

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", document=" + Arrays.toString(document) +
                '}';
    }
}
