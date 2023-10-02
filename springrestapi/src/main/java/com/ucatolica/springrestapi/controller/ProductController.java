package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Product;
import com.ucatolica.springrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //@Controller + @ResponseBody
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService pService;


    /**
     * Obtiene la lista de todos los productos.
     * @return ResponseEntity con la lista de productos y el estado HTTP OK.
     */
    //localhost:8081/api/products - Obtiene la lista de todos los productos.
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
        return new ResponseEntity<>(pService.getProducts(), HttpStatus.OK) ;
    }

    /**
     * Obtiene la información de un producto por su ID.
     * @param id El ID del producto a obtener.
     * @return ResponseEntity con el producto obtenido y el estado HTTP OK.
     */
    //localhost:8081/api/products/12 - Obtiene la información de un producto por su ID.
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return new ResponseEntity<>(pService.getSingleProduct(id), HttpStatus.OK);
    }

    /**
     * Guarda la información de un nuevo producto.
     * @param product El producto a guardar.
     * @return ResponseEntity con el producto guardado y el estado HTTP CREATED.
     */
    //localhost:8081/api/products - Guarda la información de un nuevo producto.
    @PostMapping("/products")
    public ResponseEntity <Product> saveProduct(@Valid @RequestBody Product product){
        return new ResponseEntity<> (pService.saveProduct(product), HttpStatus.CREATED);
    }

    /**
     * Actualiza la información de un producto existente.
     * @param id El ID del producto a actualizar.
     * @param product Los datos actualizados del producto.
     * @return ResponseEntity con el producto actualizado y el estado HTTP OK.
     */
    //localhost:8081/api/products/1 --  Actualiza la información de un producto existente.
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        product.setId(id);
        return new ResponseEntity<>(pService.updateProduct(product), HttpStatus.OK);
    }

    /**
     * Borra la información de un producto por su ID.
     * @param id El ID del producto a borrar.
     * @return ResponseEntity con el estado HTTP NO_CONTENT.
     */
    //localhost:8081/api/products?id=12 - Borra la información de un producto por su ID.
    @DeleteMapping ("/products")
    public ResponseEntity <HttpStatus> deleteProduct(@RequestParam Long id){
        pService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Filtra los productos por nombre.
     * @param product_name El nombre del producto a filtrar.
     * @return ResponseEntity con la lista de productos filtrados y el estado HTTP OK.
     */
    //localhost:8081/api/products/filterByName?product_name=xxx - Filtra los productos por nombre.
    @GetMapping("/products/filterByName")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String product_name){
        return new ResponseEntity<>(pService.getProductsByName(product_name), HttpStatus.OK);
    }


}
