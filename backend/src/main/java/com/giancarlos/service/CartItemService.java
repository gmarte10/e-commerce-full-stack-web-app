package com.giancarlos.service;


import com.giancarlos.model.CartItem;
import com.giancarlos.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public List<CartItem> getAllCartItemsByAccountId(int accountId) {
        return cartItemRepository.findByAccountId(accountId);
    }

    public CartItem addProductToCart(int accountId, int productId) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setAccountId(accountId);
        return cartItemRepository.save(cartItem);
    }

    public void removeProductFromCart(CartItem cartItem) {
        cartItemRepository.deleteById(cartItem.getId());
    }

    @Transactional
    public void removeProductFromAllCarts(int productId) {
        cartItemRepository.deleteByProductId(productId);
    }
}
