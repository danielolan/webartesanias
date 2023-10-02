package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Comment;

import java.util.List;

public interface CommentsService {

    List<Comment> getAllComments();

    Comment createComment(Comment comment);

    Comment getCommentById(Long commentId);


    void deleteComment(Long commentId);
}
