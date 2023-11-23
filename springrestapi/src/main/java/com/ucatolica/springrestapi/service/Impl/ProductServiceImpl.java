package com.ucatolica.springrestapi.service.Impl;


import com.ucatolica.springrestapi.model.Product;
import com.ucatolica.springrestapi.repository.ProductRepository;
import com.ucatolica.springrestapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Implementación del servicio de productos (ProductService).
 */
@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository pRepository;
    @Autowired
    private ProductRepository productRepository;

    /**
     * Obtiene la lista de todos los productos.
     * @return Lista de productos.
     */
    //Consultar
    @Override
    public List<Product> getProducts() {
        return pRepository.findAll();
    }

    /**
     * Guarda un nuevo producto.
     * @param product El producto a guardar.
     * @return El producto guardado.
     */
    //Guardar
    @Override
    public Product saveProduct(Product product) {
        return pRepository.save(product);
    }

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto a obtener.
     * @return El producto con el ID especificado.
     * @throws RuntimeException Si el producto no se encuentra.
     */
    //Consultar por id
    @Override
    public Product getSingleProduct(Long id) {
        Optional<Product> product = pRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        }
        throw new RuntimeException("Product is not found for the id" +id);}

    /**
     * Elimina un producto por su ID.
     * @param id El ID del producto a eliminar.
     */
    //Eliminar
    @Override
    public void deleteProduct(Long id) {
        pRepository.deleteById(id);
    }

    /**
     * Actualiza un producto existente.
     * @param product El producto con los datos actualizados.
     * @return El producto actualizado.
     * @throws RuntimeException Si el producto no se encuentra.
     */
    //Actualizar
    @Override
    public Product updateProduct(Product product) {
        if (!pRepository.existsById(product.getId())) {
            throw new RuntimeException("Product is not found for the id " + product.getId());
        }
        return pRepository.save(product);
    }

    /**
     * Filtra productos por nombre.
     * @param product_name El nombre del producto a filtrar.
     * @return Lista de productos con el nombre especificado.
     */
    //Filtrar por nombre
    @Override
    public List<Product> getProductsByName(String product_name) {
        return pRepository.findByProductName(product_name);
    }
    public Product updateProductInventory(Long id, Long newInventory) {
        Product product = productRepository.findById(id).orElseThrow(/* tu excepción aquí */);
        product.setProduct_inventory(newInventory);
        return productRepository.save(product);
    }
    
}
