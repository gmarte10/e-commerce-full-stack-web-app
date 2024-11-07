package com.giancarlos.repository;

import com.giancarlos.model.ProductToCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductToCategoryRepository extends JpaRepository<ProductToCategory, Integer> {
    List<ProductToCategory> findByProductId(int productId);
    List<ProductToCategory> findByCategoryId(int categoryId);
}
