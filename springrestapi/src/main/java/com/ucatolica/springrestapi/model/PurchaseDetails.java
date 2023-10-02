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
@Table(name="tbl_purchase_details")
public class PurchaseDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long details_id;

    @NotNull(message ="purchase product id should not be null")
    private Long purchase_product_id;

    @NotNull(message ="product id should not be null")
    private Long product_id;

    @NotNull(message ="purchase unit amount should not be null")
    private Long purchase_unit_amount;

    @NotNull(message ="purchase subtotal should not be null")
    private Long purchase_sub_total;

    @NotNull(message ="unit price should not be null")
    private Long unit_price;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date update_at;




}
