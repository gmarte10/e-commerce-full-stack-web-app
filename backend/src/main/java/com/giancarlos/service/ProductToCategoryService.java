package com.giancarlos.service;

import com.giancarlos.model.Category;
import com.giancarlos.model.ProductToCategory;
import com.giancarlos.repository.ProductToCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductToCategoryService {
    @Autowired
    private ProductToCategoryRepository productToCategoryRepository;

    @Autowired
    private CategoryService categoryService;


    public ProductToCategory addProductToCategory(int productId, String categoryName) {
        ProductToCategory ptc = new ProductToCategory();
        Category category = categoryService.getCategoryByName(categoryName);
        ptc.setCategoryId(category.getId());
        ptc.setProductId(productId);
        return productToCategoryRepository.save(ptc);
    }

    // Same foreign key constraint error of product having id 0
    public ProductToCategory addProductToCategory(ProductToCategory productToCategory) {
        return productToCategoryRepository.save(productToCategory);
    }

    @Transactional
    public void removeProductFromCategory(int productId) {
        List<ProductToCategory> toRemove = productToCategoryRepository.findByProductId(productId);
        for (ProductToCategory ptc : toRemove) {
            productToCategoryRepository.deleteById(ptc.getId());
        }
    }

    public List<ProductToCategory> getProductsByCategoryName(String name) {
        Category category = categoryService.getCategoryByName(name);
        return productToCategoryRepository.findByCategoryId(category.getId());
    }

    public List<ProductToCategory> getAllProductAndCategoryRelation() {
        return productToCategoryRepository.findAll();
    }

    public List<ProductToCategory> getProductCategories(int productId) {
        return productToCategoryRepository.findByProductId(productId);
    }
}


