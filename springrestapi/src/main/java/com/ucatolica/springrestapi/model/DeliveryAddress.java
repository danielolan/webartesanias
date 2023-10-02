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
@Table(name="tbl_delivery_add")
public class DeliveryAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long add_id;


    private Long user_id;

    @NotNull(message ="city should not be null")
    private String city;

    @NotNull(message ="address should not be null")
    private String address;

    @NotNull(message ="delivery price should not be null")
    private Long delivery_price;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date update_at;

}
