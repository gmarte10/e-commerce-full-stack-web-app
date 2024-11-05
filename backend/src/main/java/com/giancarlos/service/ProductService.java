package com.giancarlos.service;

import com.giancarlos.model.Product;
import com.giancarlos.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Transactional
    public void removeProductFromDbById(int productId) {
        productRepository.deleteById(productId);
    }

    public Product addProductToDb(Product product) {
        return productRepository.save(product);
    }
}
