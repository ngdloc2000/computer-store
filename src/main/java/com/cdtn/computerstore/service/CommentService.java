package com.cdtn.computerstore.service;

import com.cdtn.computerstore.entity.Comment;
import com.cdtn.computerstore.repository.comment.ICommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    @Autowired
    private ICommentRepository iCommentRepository;


    public List<Comment> getCommentByProductId(Long id) {
        return iCommentRepository.getCommentByProductIdOrderByCreatedAtDesc(id);
    }

    public void createComment(Comment comment){
        iCommentRepository.save(comment);
    }
}
