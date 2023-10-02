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
@Table(name="tbl_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //ID único del producto.


    @NotNull(message ="product name should not be null")
    private String productName; //Nombre del producto.

    @NotNull(message ="product ean code should not be null")
    private Long product_ean_code; //Código EAN del producto.

    @NotNull(message ="product brand should not be null")
    private String product_brand; //Marca del producto.

    @NotNull(message ="product description should not be null")
    private String product_description; //Descripción del producto.

    @NotNull(message ="product inventory should not be null")
    private Long product_inventory; //Inventario del producto.

    @NotNull(message ="product price should not be null")
    private Long product_price; //Precio del producto.

    @Lob
    @Column(name = "product_image")
    private byte[] product_image; // Imagen del producto.

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at; //Fecha de creación del producto.

    @UpdateTimestamp
    @Column(name="updated_at") // Fecha de actualización del producto.
    private Date update_at;
}
