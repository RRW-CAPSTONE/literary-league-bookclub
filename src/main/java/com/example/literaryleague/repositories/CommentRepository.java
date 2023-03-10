package com.example.literaryleague.repositories;

import com.example.literaryleague.models.BookDiscussion;
import com.example.literaryleague.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

}
