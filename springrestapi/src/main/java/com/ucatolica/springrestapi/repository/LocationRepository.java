package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio que gestiona las operaciones de base de datos para las ubicaciones (Location).
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
