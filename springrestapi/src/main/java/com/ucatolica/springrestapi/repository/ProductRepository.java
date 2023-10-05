package com.ucatolica.springrestapi.repository;

import com.ucatolica.springrestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio que gestiona las operaciones de base de datos para los productos (Product).
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Busca productos por nombre.
     * @param product_name El nombre del producto a buscar.
     * @return Lista de productos con el nombre especificado.
     */
    List<Product> findByProductName(String product_name);
}
