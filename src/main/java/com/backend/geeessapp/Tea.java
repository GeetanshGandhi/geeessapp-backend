package com.backend.geeessapp;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Tea {

    @Id
    private int id;
    private String author;
    private String content;

    @Column(name = "creation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Tea(){}
    public Tea(int id, String author, String content, Date date) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"author\":\"" + author + '\"' +
                ",\"content\":\"" + content + '\"' +
                ",\"date\":\"" + date.toString() + "\"" +
                '}';
    }
}
