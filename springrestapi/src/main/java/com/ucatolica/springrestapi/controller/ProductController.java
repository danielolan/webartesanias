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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController // @Controller + @ResponseBody
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService pService;
    @Autowired
    private ProductService productService;

    private final Path rootLocation = Paths.get("E:/arquitectura web/codigos u/webartesanias/front/img");

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
            String fileName = file.getOriginalFilename(); // Obtiene solo el nombre del archivo
            Path resolvePath = rootLocation.resolve(Paths.get(fileName));
            Files.copy(file.getInputStream(), resolvePath, StandardCopyOption.REPLACE_EXISTING);

            // Crear y guardar el producto
            Product product = new Product();
            product.setProductName(productName);
            product.setProduct_ean_code(productEanCode);
            product.setProduct_brand(productBrand);
            product.setProduct_description(productDescription);
            product.setProduct_inventory(productInventory);
            product.setProduct_price(productPrice);

            // Aquí guardas solo la ruta relativa "./img/" junto con el nombre del archivo
            String relativePath = "./img/" + fileName;
            product.setProduct_image(relativePath); // Guardar la ruta relativa de la imagen

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

    @PutMapping("/products/{id}/updateInventory")
    public ResponseEntity<Product> updateProductInventory(@PathVariable Long id, @RequestBody Product product) {
        // Obtener el producto actual de la base de datos
        Product existingProduct = pService.getSingleProduct(id);

        // Actualizar la cantidad de inventario
        existingProduct.setProduct_inventory(existingProduct.getProduct_inventory() - product.getProduct_inventory());

        // Guardar el producto actualizado
        return new ResponseEntity<>(pService.updateProduct(existingProduct), HttpStatus.OK);
    }

    @PatchMapping("/products/{id}")
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates) {
        Product product = pService.getSingleProduct(id);

        if (updates.containsKey("product_inventory")) {
            Object value = updates.get("product_inventory");
            Long quantityPurchased = value instanceof Integer ? ((Integer) value).longValue() : (Long) value;

            // Restar la cantidad comprada del inventario existente
            Long newInventory = product.getProduct_inventory() - quantityPurchased;

            // Asegurar que el inventario no sea negativo
            product.setProduct_inventory(Math.max(newInventory, 0L));
        }

        pService.updateProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/products/actions/preload")
    public ResponseEntity<String> preloadProducts() {
        // Crear y guardar un producto
        // Cesto Colgante de Ratán
        Product product1 = new Product();
        product1.setProductName("Cesto Colgante de Ratán");
        product1.setProduct_ean_code(101010L);
        product1.setProduct_brand("Artesanías del Valle");
        product1.setProduct_description(
                "Cesto colgante tejido a mano con ratán natural, perfecto para almacenamiento decorativo.");
        product1.setProduct_inventory(15L);
        product1.setProduct_price(45000L);
        product1.setProduct_image("./img/1.jpg");
        productService.saveProduct(product1);

        // Atrapasueños Marítimo
        Product product2 = new Product();
        product2.setProductName("Atrapasueños Marítimo");
        product2.setProduct_ean_code(202020L);
        product2.setProduct_brand("Sueños del Mar");
        product2.setProduct_description(
                "Atrapasueños con temática marina, decorado con conchas y cuentas, ideal para decoración costera.");
        product2.setProduct_inventory(20L);
        product2.setProduct_price(35000L);
        product2.setProduct_image("./img/2.jpg");
        productService.saveProduct(product2);

        // Set de Platos Decorativos
        Product product3 = new Product();
        product3.setProductName("Set de Platos Decorativos");
        product3.setProduct_ean_code(303030L);
        product3.setProduct_brand("DecorArt");
        product3.setProduct_description(
                "Conjunto de platos decorativos de mimbre, diseñados para dar un toque artesanal a cualquier pared.");
        product3.setProduct_inventory(10L);
        product3.setProduct_price(60000L);
        product3.setProduct_image("./img/3.jpg");
        productService.saveProduct(product3);

        // Atrapasueños Clásico
        Product product4 = new Product();
        product4.setProductName("Atrapasueños Clásico");
        product4.setProduct_ean_code(404040L);
        product4.setProduct_brand("Sueños Ancestrales");
        product4.setProduct_description(
                "Atrapasueños de estilo clásico con plumas naturales, para un sueño tranquilo y decoración del hogar.");
        product4.setProduct_inventory(20L);
        product4.setProduct_price(25000L);
        product4.setProduct_image("./img/4.jpg");
        productService.saveProduct(product4);

        // Guantes Tejidos Multicolor
        Product product5 = new Product();
        product5.setProductName("Guantes Tejidos Multicolor");
        product5.setProduct_ean_code(505050L);
        product5.setProduct_brand("ColorKnit");
        product5.setProduct_description(
                "Guantes de lana tejidos a mano, cálidos y con un diseño colorido para alegrar los días fríos.");
        product5.setProduct_inventory(25L);
        product5.setProduct_price(20000L);
        product5.setProduct_image("./img/5.jpg");
        productService.saveProduct(product5);

        // Atrapasueños Vibrante
        Product product6 = new Product();
        product6.setProductName("Atrapasueños Vibrante");
        product6.setProduct_ean_code(606060L);
        product6.setProduct_brand("VibrantDreams");
        product6.setProduct_description(
                "Atrapasueños con colores vibrantes y plumas, añade un toque de color y energía positiva a cualquier habitación.");
        product6.setProduct_inventory(18L);
        product6.setProduct_price(30000L);
        product6.setProduct_image("./img/6.jpg");
        productService.saveProduct(product6);

        // Atrapasueños Minimalista
        Product product7 = new Product();
        product7.setProductName("Atrapasueños Minimalista");
        product7.setProduct_ean_code(707070L);
        product7.setProduct_brand("MinimalDream");
        product7.setProduct_description(
                "Atrapasueños minimalista con un diseño sencillo y elegante, ideal para la decoración moderna del hogar.");
        product7.setProduct_inventory(22L);
        product7.setProduct_price(28000L);
        product7.setProduct_image("./img/7.jpg");
        productService.saveProduct(product7);

        // Alfarería Tradicional
        Product product8 = new Product();
        product8.setProductName("Alfarería Tradicional");
        product8.setProduct_ean_code(808080L);
        product8.setProduct_brand("ClayCraft");
        product8.setProduct_description(
                "Piezas de alfarería hechas a mano por artesanos locales, con diseños y pinturas tradicionales.");
        product8.setProduct_inventory(8L);
        product8.setProduct_price(50000L);
        product8.setProduct_image("./img/8.jpg");
        productService.saveProduct(product8);

        // Maceta de Cerámica Pintada
        Product product9 = new Product();
        product9.setProductName("Maceta de Cerámica Pintada");
        product9.setProduct_ean_code(909090L);
        product9.setProduct_brand("CeramicGarden");
        product9.setProduct_description(
                "Macetas de cerámica pintadas a mano, cada una con un diseño único para realzar tus plantas.");
        product9.setProduct_inventory(12L);
        product9.setProduct_price(40000L);
        product9.setProduct_image("./img/9.jpg");
        productService.saveProduct(product9);

        // Continuar agregando más productos si es necesario

        return ResponseEntity.ok("Productos pre-cargados con éxito");
    }

}
