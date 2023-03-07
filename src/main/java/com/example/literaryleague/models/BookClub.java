package com.example.literaryleague.models;

import jakarta.persistence.*;

import java.util.List;

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

    @OneToOne
    private Book current_book;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookClub")
    private List<BookDiscussion> bookDiscussions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bookClub")
    private List<SuggestedBook> suggestedBooks;



    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "clubs_users",
            joinColumns = {@JoinColumn(name = "bc_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "club_book",
            joinColumns = {@JoinColumn(name = "bc_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> books;

    public BookClub() {
    }

    public BookClub(long id, String title, String description, User user, List<User> users, List<Book> books) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user = user;
        this.users = users;
        this.books = books;
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

