package com.example.literaryleague.models;

import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "discussion_id")
    private BookDiscussion bookDiscussion;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    public Comment() {
    }

    public Comment(long id, String body, BookDiscussion bookDiscussion, User user) {
        this.id = id;
        this.body = body;
        this.bookDiscussion = bookDiscussion;
        this.user = user;
    }

    public Comment(String body, BookDiscussion bookDiscussion, User user) {
        this.body = body;
        this.bookDiscussion = bookDiscussion;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public BookDiscussion getBookDiscussion() {
        return bookDiscussion;
    }

    public void setBookDiscussion(BookDiscussion bookDiscussion) {
        this.bookDiscussion = bookDiscussion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

