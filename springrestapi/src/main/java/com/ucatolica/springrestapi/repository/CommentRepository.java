package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones de base de datos para los comentarios (Comment).
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
