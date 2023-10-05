package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Comment;

import java.util.List;

/**
 * Interfaz que define los métodos para el servicio de comentarios (Comment).
 */
public interface CommentsService {

    /**
     * Obtiene todos los comentarios.
     *
     * @return Una lista de todos los comentarios.
     */
    List<Comment> getAllComments();

    /**
     * Crea un nuevo comentario.
     *
     * @param comment El comentario a crear.
     * @return El comentario creado.
     */
    Comment createComment(Comment comment);

    /**
     * Obtiene un comentario por su ID.
     *
     * @param commentId El ID del comentario a obtener.
     * @return El comentario encontrado o null si no se encuentra.
     */
    Comment getCommentById(Long commentId);

    /**
     * Elimina un comentario por su ID.
     *
     * @param commentId El ID del comentario a eliminar.
     */
    void deleteComment(Long commentId);
}
