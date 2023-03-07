package com.example.literaryleague.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Book> books;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BookDiscussion> bookDiscussions;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<BookClub> clubs;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserReviews> userReviews;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<SuggestedBook> suggestedBooks;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments;

    @ManyToMany(mappedBy = "users")
    private List<BookClub> bookClubs;

    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(long id, String username, String email, String password, List<Book> books, List<BookDiscussion> bookDiscussions, List<BookClub> clubs, List<UserReviews> userReviews, List<SuggestedBook> suggestedBooks, List<Comment> comments, List<BookClub> bookClubs) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.books = books;
        this.bookDiscussions = bookDiscussions;
        this.clubs = clubs;
        this.userReviews = userReviews;
        this.suggestedBooks = suggestedBooks;
        this.comments = comments;
        this.bookClubs = bookClubs;
    }

    public User(String username, String email, String password, List<Book> books, List<BookDiscussion> bookDiscussions, List<BookClub> clubs, List<UserReviews> userReviews, List<SuggestedBook> suggestedBooks, List<Comment> comments, List<BookClub> bookClubs) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.books = books;
        this.bookDiscussions = bookDiscussions;
        this.clubs = clubs;
        this.userReviews = userReviews;
        this.suggestedBooks = suggestedBooks;
        this.comments = comments;
        this.bookClubs = bookClubs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookDiscussion> getBookDiscussions() {
        return bookDiscussions;
    }

    public void setBookDiscussions(List<BookDiscussion> bookDiscussions) {
        this.bookDiscussions = bookDiscussions;
    }

    public List<BookClub> getClubs() {
        return clubs;
    }

    public void setClubs(List<BookClub> clubs) {
        this.clubs = clubs;
    }

    public List<UserReviews> getUserReviews() {
        return userReviews;
    }

    public void setUserReviews(List<UserReviews> userReviews) {
        this.userReviews = userReviews;
    }

    public List<SuggestedBook> getSuggestedBooks() {
        return suggestedBooks;
    }

    public void setSuggestedBooks(List<SuggestedBook> suggestedBooks) {
        this.suggestedBooks = suggestedBooks;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<BookClub> getBookClubs() {
        return bookClubs;
    }

    public void setBookClubs(List<BookClub> bookClubs) {
        this.bookClubs = bookClubs;
    }
}
