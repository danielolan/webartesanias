package com.ucatolica.springrestapi.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

/**
 * Clase que representa una compra.
 */
@Setter
@Getter
@ToString
@Entity
@Table(name = "tbl_purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchase_id;



    @NotNull(message = "unit price should not be null")
    private Long unit_price;

    @NotNull(message = "product amount should not be null")
    private Long amount_product;


    @NotNull(message = "purchase total should not be null")
    private Long purchase_total;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
