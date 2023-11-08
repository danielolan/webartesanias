package com.ucatolica.springrestapi.controller;

import com.ucatolica.springrestapi.model.Product;
import com.ucatolica.springrestapi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
// asegúrate de importar Path y otras clases relacionadas aquí
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import java.util.List;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService pService;

    private final Path rootLocation = Paths.get("C:/Users/Daniel/Documents/aprendiendoweb/webartesanias/front/img");

    /**
     * Obtiene la lista de todos los productos.
     * 
     * @return ResponseEntity con la lista de productos y el estado HTTP OK.
     */
    // localhost:8081/api/products - Obtiene la lista de todos los productos.
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return new ResponseEntity<>(pService.getProducts(), HttpStatus.OK);
    }

    /**
     * Obtiene la información de un producto por su ID.
     * 
     * @param id El ID del producto a obtener.
     * @return ResponseEntity con el producto obtenido y el estado HTTP OK.
     */
    // localhost:8081/api/products/12 - Obtiene la información de un producto por su
    // ID.
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(pService.getSingleProduct(id), HttpStatus.OK);
    }

    /**
     * Guarda la información de un nuevo producto.
     * 
     * @param product El producto a guardar.
     * @return ResponseEntity con el producto guardado y el estado HTTP CREATED.
     */
    // localhost:8081/api/products - Guarda la información de un nuevo producto.
    @PostMapping("/products")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<>(pService.saveProduct(product), HttpStatus.CREATED);
    }

    /**
     * Actualiza la información de un producto existente.
     * 
     * @param id      El ID del producto a actualizar.
     * @param product Los datos actualizados del producto.
     * @return ResponseEntity con el producto actualizado y el estado HTTP OK.
     */
    // localhost:8081/api/products/1 -- Actualiza la información de un producto
    // existente.
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        return new ResponseEntity<>(pService.updateProduct(product), HttpStatus.OK);
    }

    /**
     * Borra la información de un producto por su ID.
     * 
     * @param id El ID del producto a borrar.
     * @return ResponseEntity con el estado HTTP NO_CONTENT.
     */
    // localhost:8081/api/products?id=12 - Borra la información de un producto por
    // su ID.
    @DeleteMapping("/products")
    public ResponseEntity<HttpStatus> deleteProduct(@RequestParam Long id) {
        pService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/products/upload")
    public ResponseEntity<Product> handleFileUpload(@RequestParam("product_image") MultipartFile file,
            @RequestParam("productName") String productName,
            @RequestParam("product_ean_code") Long productEanCode,
            @RequestParam("product_brand") String productBrand,
            @RequestParam("product_description") String productDescription,
            @RequestParam("product_inventory") Long productInventory,
            @RequestParam("product_price") Long productPrice) {
        try {
            // Asegúrate de que el directorio exista
            Files.createDirectories(rootLocation);

            // Construye la ruta del archivo y guarda el archivo
            Path resolvePath = rootLocation.resolve(Paths.get(file.getOriginalFilename()));
            Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);

            // Crear y guardar el producto
            Product product = new Product();
            product.setProductName(productName);
            product.setProduct_ean_code(productEanCode);
            product.setProduct_brand(productBrand);
            product.setProduct_description(productDescription);
            product.setProduct_inventory(productInventory);
            product.setProduct_price(productPrice);
            product.setProduct_image(resolvePath.toString()); // Guardar la ruta de la imagen o algo que represente la
                                                              // imagen
            Product savedProduct = pService.saveProduct(product);

            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Filtra los productos por nombre.
     * 
     * @param product_name El nombre del producto a filtrar.
     * @return ResponseEntity con la lista de productos filtrados y el estado HTTP
     *         OK.
     */
    // localhost:8081/api/products/filterByName?product_name=xxx - Filtra los
    // productos por nombre.
    @GetMapping("/products/filterByName")
    public ResponseEntity<List<Product>> getProductsByName(@RequestParam String product_name) {
        return new ResponseEntity<>(pService.getProductsByName(product_name), HttpStatus.OK);
    }

}
