package com.giancarlos.controller;

import com.giancarlos.model.Account;
import com.giancarlos.model.CartItem;
import com.giancarlos.model.Product;
import com.giancarlos.service.AccountService;
import com.giancarlos.service.CartItemService;
import com.giancarlos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cartItem")
    public ResponseEntity<List<Product>> getAllCartItemsByAccountId(@RequestParam String username) {
        int accountId = accountService.getAccountId(username);
        List<CartItem> cartItems = cartItemService.getAllCartItemsByAccountId(accountId);
        List<Product> products = new ArrayList<>();
        for (CartItem item : cartItems) {
            Product product = productService.findProductById(item.getProductId());
            products.add(product);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/cartItem/{username}/{productId}")
    public ResponseEntity<CartItem> addProductToCart(@PathVariable String username, @PathVariable int productId) {
        int accountId = accountService.getAccountId(username);
        return new ResponseEntity<>(cartItemService.addProductToCart(accountId, productId), HttpStatus.CREATED);
    }

    @DeleteMapping("/cartItem/{username}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable String username, @PathVariable int productId) {
        int accountId = accountService.getAccountId(username);
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
