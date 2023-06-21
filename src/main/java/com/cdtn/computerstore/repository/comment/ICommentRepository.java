package com.cdtn.computerstore.repository.comment;

import com.cdtn.computerstore.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> getCommentByProductIdOrderByCreatedAtDesc(Long id);
}
