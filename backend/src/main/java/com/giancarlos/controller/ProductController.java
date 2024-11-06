package com.giancarlos.controller;

import com.giancarlos.model.Account;
import com.giancarlos.model.CartItem;
import com.giancarlos.model.Product;
import com.giancarlos.service.CartItemService;
import com.giancarlos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable int productId) {
        return new ResponseEntity<>(productService.findProductById(productId), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProductToDb(@RequestBody Product product) {
        Product p = productService.addProductToDb(product);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> addProductToDataBase(@RequestParam("name") String name,
                                                        @RequestParam("price") int price,
                                                        @RequestParam("description") String description,
                                                        @RequestParam("image")MultipartFile imageFile) {

        try {
            Path uploadPath = Paths.get(System.getProperty("user.dir"), uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            String fileName = imageFile.getOriginalFilename();
            Path imagePath = uploadPath.resolve(fileName);
            imageFile.transferTo(imagePath.toFile());
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setImage(fileName);

            Product savedProduct = productService.addProductToDb(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        } catch(IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> removeProductFromDbById(@PathVariable int productId) {
        cartItemService.removeProductFromAllCarts(productId);
        productService.removeProductFromDbById(productId);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

}

