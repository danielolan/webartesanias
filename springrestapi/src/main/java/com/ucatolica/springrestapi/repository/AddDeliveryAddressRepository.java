package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.DeliveryAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddDeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
}
