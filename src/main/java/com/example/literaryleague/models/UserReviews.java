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

    public UserReviews(long id, String review) {
        this.id = id;
        this.review = review;
    }

    public UserReviews(String review) {
        this.review = review;
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
}

