package com.giancarlos.controller;

import com.giancarlos.model.Account;
import com.giancarlos.model.CartItem;
import com.giancarlos.model.Product;
import com.giancarlos.service.CartItemService;
import com.giancarlos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

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

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> removeProductFromDbById(@PathVariable int productId) {
        cartItemService.removeProductFromAllCarts(productId);
        productService.removeProductFromDbById(productId);
        return new ResponseEntity<>("successfully deleted", HttpStatus.OK);
    }

}

