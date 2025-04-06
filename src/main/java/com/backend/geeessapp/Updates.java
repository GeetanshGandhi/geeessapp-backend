package com.backend.geeessapp;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Updates {
    @Id
    private int id;
    private String caption;
    private String author;

    @Column(name = "creation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] image;
    public Updates(){}
    public Updates(int id, String caption, byte[] image, String author, Date date) {
        this.id = id;
        this.caption = caption;
        this.image = image;
        this.author = author;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
