package com.example.booklibrary1.model;

public class model {
    Long id;
    String title;

    public model(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    String author;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
