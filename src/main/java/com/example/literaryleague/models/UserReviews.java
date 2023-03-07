package com.example.literaryleague.models;

import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class UserReviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private  User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private  Book book;

    public UserReviews(){}

    public UserReviews(long id, String review, User user, Book book) {
        this.id = id;
        this.review = review;
        this.user = user;
        this.book = book;
    }

    public UserReviews(String review, User user, Book book) {
        this.review = review;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}

