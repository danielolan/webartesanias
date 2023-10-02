package com.ucatolica.springrestapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
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
@Table(name = "tbl_employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message ="Name should not be null")
    private String name;

    private Long age = 0L;

    @NotNull(message ="Location should not be null")
    private String location;

    @jakarta.validation.constraints.Email(message ="please enter a valid email address")
    private String email;

    @NotNull(message ="Department should not be null")
    private String department;

    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(name="updated_at")
    private Date update_at;


}
