package com.example.literaryleague.models;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "genres")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    public Genre(){}

    public Genre(long id, String genre, List<Book> books){
        this.id = id;
        this.genre = genre;
        this.books = books;
    }

    public Genre(String genre, List<Book> books) {
        this.genre = genre;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
