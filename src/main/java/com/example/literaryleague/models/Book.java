package com.example.literaryleague.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String genre;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(
//            name = "books_genres",
//            joinColumns = {@JoinColumn(name="book_id")},
//            inverseJoinColumns = {@JoinColumn(name="genre_id")}
    )
    private List<Genre> genres;

    public Book(){}

    public Book(long id, String title, String author, String genre, User user, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.user = user;
        this.genres = genres;
    }

    public Book(long id, String title, String author, String genre, User user) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.user = user;
    }

    public Book(String title, String author, String genre, User user) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.user = user;
    }

    public Book(String title, String author, String genre, User user, List<Genre> genres) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.user = user;
        this.genres = genres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
