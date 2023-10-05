package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Comment;
import com.ucatolica.springrestapi.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador que maneja las solicitudes relacionadas con los comentarios (Comments).
 */
@RestController
@RequestMapping("/api")
public class CommentsController {

    @Autowired
    private CommentsService cService;

    /**
     * Obtiene todos los comentarios disponibles.
     *
     * @return Una ResponseEntity que contiene una lista de objetos Comment y el estado HTTP OK (200).
     */
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(cService.getAllComments(), HttpStatus.OK);
    }

    /**
     * Obtiene un comentario específico por su identificador.
     *
     * @param id El identificador único del comentario.
     * @return Una ResponseEntity que contiene el comentario correspondiente al ID proporcionado y el estado HTTP OK (200).
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(cService.getCommentById(id), HttpStatus.OK);
    }

    /**
     * Crea un nuevo comentario.
     *
     * @param comment El comentario a ser creado y validado con anotaciones de Jakarta Validation.
     * @return Una ResponseEntity que contiene el comentario creado y el estado HTTP CREATED (201).
     */
    @PostMapping("/comments")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        return new ResponseEntity<>(cService.createComment(comment), HttpStatus.CREATED);
    }

    /**
     * Elimina un comentario por su identificador.
     *
     * @param id El identificador único del comentario a ser eliminado.
     * @return Una ResponseEntity con el estado HTTP NO_CONTENT (204) después de eliminar el comentario.
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        cService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
