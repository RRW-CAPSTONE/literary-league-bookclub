package com.example.literaryleague.repositories;

import com.example.literaryleague.models.BookClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookClubRepository extends JpaRepository<BookClub, Long> {
    BookClub findBookClubById(long id);

}
