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

    @Column
    private String description;

    @Column
    private String isbn;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Book(long id, String title, String author, String description, List<SuggestedBook> suggestedBooks, User user, List<UserReviews> userReviews, List<BookClub> bookClubs) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.suggestedBooks = suggestedBooks;
        this.user = user;
        this.userReviews = userReviews;
        this.bookClubs = bookClubs;
    }

    public Book(String title, String author, String description, List<SuggestedBook> suggestedBooks, User user, List<UserReviews> userReviews, List<BookClub> bookClubs) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.suggestedBooks = suggestedBooks;
        this.user = user;
        this.userReviews = userReviews;
        this.bookClubs = bookClubs;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SuggestedBook> getSuggestedBooks() {
        return suggestedBooks;
    }

    public void setSuggestedBooks(List<SuggestedBook> suggestedBooks) {
        this.suggestedBooks = suggestedBooks;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserReviews> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReviews> userReviews) {
        this.userReviews = userReviews;
    }

    public List<BookClub> getBookClubs() {
        return bookClubs;
    }

    public void setBookClubs(List<BookClub> bookClubs) {
        this.bookClubs = bookClubs;
    }
}
