package com.example.literaryleague.repositories;

import com.example.literaryleague.models.BookClub;
import com.example.literaryleague.models.BookDiscussion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDiscussionRepository extends JpaRepository<BookDiscussion, Long> {
    BookDiscussion findBookDiscussionById(long id);

    List<BookDiscussion> findByBookClub(BookClub club);
}
