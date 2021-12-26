package com.projects.sarafan.service;

import com.projects.sarafan.domain.Comment;
import com.projects.sarafan.domain.User;
import com.projects.sarafan.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        return commentRepository.save(comment);
    }
}
