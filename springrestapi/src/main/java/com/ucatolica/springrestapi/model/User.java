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
 * Clase que representa un usuario en la aplicación.
 */
@Setter
@Getter
@ToString
@Entity
@Table(name="tbl_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @NotNull(message ="password should not be null")
    private String password;

    @NotNull(message ="user rol should not be null")
    private String user_rol;

    @NotNull(message ="first name should not be null")
    private String first_name;


    @NotNull(message ="last name should not be null")
    private String last_name;

    @jakarta.validation.constraints.Email(message ="Please enter correct email")
    private String user_email;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at; //Fecha de creación del producto.

    @UpdateTimestamp
    @Column(name="updated_at") // Fecha de actualización del producto.
    private Date update_at;
}
