package com.example.literaryleague.repositories;
import com.example.literaryleague.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreById(long id);
}
