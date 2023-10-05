package com.ucatolica.springrestapi.service.Impl;

import com.ucatolica.springrestapi.model.Comment;
import com.ucatolica.springrestapi.repository.CommentRepository;
import com.ucatolica.springrestapi.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de comentarios (CommentsService).
 */
@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Obtiene todos los comentarios, ordenados por fecha de creación en orden descendente.
     *
     * @return Una lista de todos los comentarios ordenados por fecha de creación descendente.
     */
    @Override
    public List<Comment> getAllComments() {
        // Crear un objeto Sort para ordenar los comentarios por fecha de
        // creación en orden descendente (del más reciente al menos reciente)
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");

        // Utilizar el método findAll con el objeto Sort
        return commentRepository.findAll(sort);
    }

    /**
     * Crea un nuevo comentario.
     *
     * @param comment El comentario a crear.
     * @return El comentario creado.
     */
    @Override
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    /**
     * Obtiene un comentario por su ID.
     *
     * @param commentId El ID del comentario a obtener.
     * @return El comentario encontrado.
     * @throws RuntimeException Si no se encuentra el comentario con el ID especificado.
     */
    @Override
    public Comment getCommentById(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if (comment.isPresent()) {
            return comment.get();
        }
        throw new RuntimeException("Comment not found with ID: " + commentId);
    }

    /**
     * Elimina un comentario por su ID.
     *
     * @param commentId El ID del comentario a eliminar.
     * @throws RuntimeException Si no se encuentra el comentario con el ID especificado.
     */
    @Override
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found with ID: " + commentId));

        commentRepository.delete(comment);
    }
}
