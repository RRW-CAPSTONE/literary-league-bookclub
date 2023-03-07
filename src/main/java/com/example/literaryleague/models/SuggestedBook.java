package com.example.literaryleague.models;

import jakarta.persistence.*;

@Entity
@Table(name="suggestedBooks")
public class SuggestedBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "bc_id")
    private BookClub bookClub;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public SuggestedBook() {
    }

    public SuggestedBook(long id, BookClub bookClub, User user, Book book) {
        this.id = id;
        this.bookClub = bookClub;
        this.user = user;
        this.book = book;
    }

    public SuggestedBook(BookClub bookClub, User user, Book book) {
        this.bookClub = bookClub;
        this.user = user;
        this.book = book;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
