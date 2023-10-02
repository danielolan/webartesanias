package com.ucatolica.springrestapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_comments")
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "El campo 'contenido' no debe ser nulo")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Product producto;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at; //Fecha de creación del comenatrio del producto.



}