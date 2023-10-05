package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones de base de datos para las compras (Purchase).
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
