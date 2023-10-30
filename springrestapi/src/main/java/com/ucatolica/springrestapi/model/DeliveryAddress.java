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
 * Clase que representa una dirección de entrega del producto.
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_delivery_add")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long add_id;

    @NotNull(message = "The city should not be null")
    private String city;

    @NotNull(message = "The address must not be null")
    private String address;

    @NotNull(message = "The delivery price must not be zero")
    private Long delivery_price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date update_at;
}
