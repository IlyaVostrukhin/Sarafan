package com.projects.sarafan.service;

import com.projects.sarafan.domain.Comment;
import com.projects.sarafan.domain.User;
import com.projects.sarafan.domain.Views;
import com.projects.sarafan.dto.EventType;
import com.projects.sarafan.dto.ObjectType;
import com.projects.sarafan.repository.CommentRepository;
import com.projects.sarafan.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepository commentRepository, WsSender wsSender) {
        this.commentRepository = commentRepository;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user){
        comment.setAuthor(user);
        Comment commentFromDb = commentRepository.save(comment);
        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}
