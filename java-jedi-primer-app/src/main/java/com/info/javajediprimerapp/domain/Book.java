package com.info.javajediprimerapp.domain;

public class Book {
    private Long id;
    private String title;
    private String author;

    public Book(Long id, String name, String author) {
        this.id = id;
        this.title = name;
        this.author = author;
    }

    public Book() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
