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
@Table(name="tbl_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchase_id;


    @NotNull(message ="user id should not be null")
    private Long user_id;

    @NotNull(message ="payment id should not be null")
    private Long payment_id;

    @NotNull(message ="purchase date should not be null")
    private Date purchase_date;

    @NotNull(message ="shipmen date should not be null")
    private Date shipment_date;

    @NotNull(message ="unit price should not be null")
    private Long unit_price;

    @NotNull(message ="purchase subtotal should not be null")
    private Long purchase_sub_total;

    @NotNull(message ="purchase total should not be null")
    private Long purchase_total;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date update_at;
}
