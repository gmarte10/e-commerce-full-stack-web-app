package com.giancarlos.controller;


import com.giancarlos.model.ProductToCategory;
import com.giancarlos.service.ProductToCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductToCategoryController {
    @Autowired
    ProductToCategoryService productToCategoryService;

    @GetMapping("/ProductToCategory/{categoryName}")
    public ResponseEntity<List<ProductToCategory>> getProductsByCategoryName(@PathVariable String categoryName) {
        return new ResponseEntity<>(productToCategoryService.getProductsByCategoryName(categoryName), HttpStatus.OK);
    }

    @PostMapping("/ProductToCategory")
    public ResponseEntity<ProductToCategory> addProductToCategory(@RequestBody ProductToCategory ptc) {
        return new ResponseEntity<>(productToCategoryService.addProductToCategory(ptc), HttpStatus.CREATED);
    }

    @PostMapping("/ProductToCategory/{productId}/{categoryName}")
    public ResponseEntity<ProductToCategory> addProductToCategory(@PathVariable int productId, @PathVariable String categoryName) {
        return new ResponseEntity<>(productToCategoryService.addProductToCategory(productId, categoryName), HttpStatus.CREATED);
    }

    @DeleteMapping("/ProductToCategory/{productId}")
    public ResponseEntity<String> removeProductFromCategory(@PathVariable int productId) {
        productToCategoryService.removeProductFromCategory(productId);
        return new ResponseEntity<>("removed product from category", HttpStatus.OK);
    }

    @GetMapping("/ProductToCategory")
    public ResponseEntity<List<ProductToCategory>> getAllProductsAndCategoryRelation() {
        return new ResponseEntity<>(productToCategoryService.getAllProductAndCategoryRelation(), HttpStatus.OK);
    }
}
