package com.example.literaryleague.repositories;

import com.example.literaryleague.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByTitle(String title);
    Book findByAuthor(String author);

    Book findBookById(long id);
    @Query("from Book b where b.title like %:term%")
    List<Book> searchByTitleLike(@Param("term") String term);

    List<Book> findAllByOrderByTitleAsc();
}
