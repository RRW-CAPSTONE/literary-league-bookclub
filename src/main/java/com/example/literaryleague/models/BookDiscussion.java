package com.example.literaryleague.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "discussion")
public class BookDiscussion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookDiscussion")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "bc_id")
    private BookClub bookClub;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BookDiscussion(){}

    public BookDiscussion(long id, String title, String location, String date, String time, List<Comment> comments, BookClub bookClub, User user) {
        this.id = id;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.bookClub = bookClub;
        this.user = user;
    }

    public BookDiscussion(String title, String location, String date, String time, List<Comment> comments, BookClub bookClub, User user) {
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.comments = comments;
        this.bookClub = bookClub;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public BookClub getBookClub() {
        return bookClub;
    }

    public void setBookClub(BookClub bookClub) {
        this.bookClub = bookClub;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void addComment(String commentText, User user) {
    }

}
