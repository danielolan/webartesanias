package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Comment;
import com.ucatolica.springrestapi.repository.CommentRepository;
import com.ucatolica.springrestapi.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getAllComments() {
        // Crear un objeto Sort para ordenar los comentarios por fecha de
        // creación en orden descendente (del más reciente al menos reciente)
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        // Utilizar el método findAll con el objeto Sort
        return commentRepository.findAll(sort);
    }
    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return comment.get();
        }
        throw new RuntimeException("Comentario no encontrado con ID: " + commentId);
    }


    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado con ID: " + commentId));

        commentRepository.delete(comment);
    }
}
