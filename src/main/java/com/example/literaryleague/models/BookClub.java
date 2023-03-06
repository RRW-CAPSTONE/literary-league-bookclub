package com.example.literaryleague.models;

import jakarta.persistence.*;
@Entity
@Table(name = "bookclubs")
public class BookClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToMany(cascade = {CascadeType.ALL})
//    private List<User> users;

    public BookClub() {
    }

    public BookClub(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public BookClub(long id, String title, String description, User user) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

