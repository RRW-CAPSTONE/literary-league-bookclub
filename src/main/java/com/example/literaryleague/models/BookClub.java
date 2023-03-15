package com.example.literaryleague.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookclubs")
@EntityListeners(AuditingEntityListener.class)
public class BookClub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

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

    public BookClub(long id, String title, String description, LocalDateTime createdAt, Book current_book, User user, List<BookDiscussion> bookDiscussions, List<SuggestedBook> suggestedBooks, List<User> users, List<Book> books) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.current_book = current_book;
        this.user = user;
        this.bookDiscussions = bookDiscussions;
        this.suggestedBooks = suggestedBooks;
        this.users = users;
        this.books = books;
    }

    public BookClub(String title, String description, LocalDateTime createdAt, Book current_book, User user, List<BookDiscussion> bookDiscussions, List<SuggestedBook> suggestedBooks, List<User> users, List<Book> books) {
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.current_book = current_book;
        this.user = user;
        this.bookDiscussions = bookDiscussions;
        this.suggestedBooks = suggestedBooks;
        this.users = users;
        this.books = books;
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

    public Book getCurrent_book() {
        return current_book;
    }

    public void setCurrent_book(Book current_book) {
        this.current_book = current_book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BookDiscussion> getBookDiscussions() {
        return bookDiscussions;
    }

    public void setBookDiscussions(List<BookDiscussion> bookDiscussions) {
        this.bookDiscussions = bookDiscussions;
    }
    public void addDiscussion(BookDiscussion discussion){bookDiscussions.add(discussion);
    }

    public List<SuggestedBook> getSuggestedBooks() {
        return suggestedBooks;
    }

    public void setSuggestedBooks(List<SuggestedBook> suggestedBooks) {
        this.suggestedBooks = suggestedBooks;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    public void addUser(User user){
        users.add(user);
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setBook(Book book){}
}

