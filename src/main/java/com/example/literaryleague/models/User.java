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
    private List<BookClub> clubs;

//    @ManyToMany(mappedBy = "Users")
//    private List<BookClub> bookClubs;

    public User() {
    }

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }

    public User(long id, String name) {
        this.id = id;
        this.username = name;
    }

    public User(long id, String username, String email, String password, List<Book> books, List<BookClub> clubs) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.books = books;
        this.clubs = clubs;
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

    public List<BookClub> getClubs() {
        return clubs;
    }

    public void setClubs(List<BookClub> clubs) {
        this.clubs = clubs;
    }
}
