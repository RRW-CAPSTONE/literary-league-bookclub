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
}
