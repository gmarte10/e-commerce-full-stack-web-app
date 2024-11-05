package com.giancarlos.repository;

import com.giancarlos.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    List<CartItem> findByAccountId(int accountId);

    void deleteByProductId(int productId);
}
