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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<SuggestedBook> suggestedBooks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "book")
    private List<UserReviews> userReviews;

    @ManyToMany(mappedBy = "books")
    private List<BookClub> bookClubs;

    public Book(){}

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

}
