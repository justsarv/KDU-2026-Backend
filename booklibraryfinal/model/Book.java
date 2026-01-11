package com.example.booklibraryfinal.model;

import jakarta.validation.constraints.NotBlank;

public class Book {
    long   id;
    @NotBlank
    String name;
    @NotBlank
    String author;

    public Book(long id, String name, String author){
        this.id=id;
        this.name=name;
        this.author=author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}
