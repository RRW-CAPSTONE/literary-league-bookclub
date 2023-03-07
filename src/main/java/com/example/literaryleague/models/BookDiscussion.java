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
    private String body;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookDiscussion")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "bc_id")
    private BookClub bookClub;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public BookDiscussion(){}

    public BookDiscussion(long id, String title, String body, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.comments = comments;
    }

    public BookDiscussion(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public BookDiscussion(String title, String body, List<Comment> comments) {
        this.title = title;
        this.body = body;
        this.comments = comments;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
