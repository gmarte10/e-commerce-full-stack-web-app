package com.giancarlos.controller;

import com.giancarlos.model.CartItem;
import com.giancarlos.model.Product;
import com.giancarlos.service.CartItemService;
import com.giancarlos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @GetMapping("/cartItem")
    public ResponseEntity<List<CartItem>> getAllCartItemsByAccountId(@RequestParam int accountId) {
        return new ResponseEntity<>(cartItemService.getAllCartItemsByAccountId(accountId), HttpStatus.OK);
    }

    @PostMapping("/cartItem/{accountId}/{productId}")
    public ResponseEntity<CartItem> addProductToCart(@PathVariable int accountId, @PathVariable int productId) {
        return new ResponseEntity<>(cartItemService.addProductToCart(accountId, productId), HttpStatus.CREATED);
    }

    @DeleteMapping("/cartItem/{accountId}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable int accountId, @PathVariable int productId) {
        List<CartItem> cartItems = cartItemService.getAllCartItemsByAccountId(accountId);
        CartItem itemToDelete = null;
        for (CartItem item : cartItems) {
            if (item.getProductId() == productId) {
                itemToDelete = item;
            }
        }
        if (itemToDelete == null) {
            return  new ResponseEntity<>("No more items to delete", HttpStatus.OK);
        }
        cartItemService.removeProductFromCart(itemToDelete);
        return new ResponseEntity<>("product removed from cart", HttpStatus.OK);
    }
}
