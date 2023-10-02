package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Comment;
import com.ucatolica.springrestapi.service.CommentsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController{
    @Autowired
    private CommentsService cService;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments() {
        return new ResponseEntity<>(cService.getAllComments(), HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return new ResponseEntity<>(cService.getCommentById(id), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity <Comment> createComment(@Valid @RequestBody Comment comment) {
        return new ResponseEntity<> (cService.createComment(comment), HttpStatus.CREATED);
    }




    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable Long id) {
        cService.deleteComment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
