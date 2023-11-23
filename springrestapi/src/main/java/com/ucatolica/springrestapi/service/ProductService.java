package com.ucatolica.springrestapi.service;

import com.ucatolica.springrestapi.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Obtiene la lista de todos los productos.
     * @return Lista de productos.
     */
    //Consultar
    List<Product> getProducts();

    /**
     * Guarda un nuevo producto.
     * @param product El producto a guardar.
     * @return El producto guardado.
     */
    //Guardar
    Product saveProduct(Product product);

    /**
     * Obtiene un producto por su ID.
     * @param id El ID del producto a obtener.
     * @return El producto con el ID especificado.
     */
    //Conssultar por id
    Product getSingleProduct(Long id);

    /**
     * Elimina un producto por su ID.
     * @param id El ID del producto a eliminar.
     */
    //Eliminar
    void deleteProduct(Long id);

    /**
     * Actualiza un producto existente.
     * @param product El producto con los datos actualizados.
     * @return El producto actualizado.
     */
    //Actualizar
    Product updateProduct(Product product);

    /**
     * Filtra productos por nombre.
     * @param product_name El nombre del producto a filtrar.
     * @return Lista de productos con el nombre especificado.
     */
    //Filtar por nombre
    List <Product> getProductsByName(String product_name);

    Product updateProductInventory(Long id, Long newInventory);
}
