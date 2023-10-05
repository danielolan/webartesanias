package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones de base de datos para las direcciones de entrega (DeliveryAddress).
 */
@Repository
public interface AddDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
