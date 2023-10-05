package com.ucatolica.springrestapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

/**
 * Clase que representa un comentario del producto.
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "The description field must not be null")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private Date createdAt; // Fecha de creación del comentario del producto.
}
