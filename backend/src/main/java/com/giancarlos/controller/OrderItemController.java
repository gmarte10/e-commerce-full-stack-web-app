package com.giancarlos.controller;

import com.giancarlos.model.CartItem;
import com.giancarlos.model.OrderItem;
import com.giancarlos.model.Product;
import com.giancarlos.service.AccountService;
import com.giancarlos.service.OrderItemService;
import com.giancarlos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    @GetMapping("/orderItem")
    public ResponseEntity<List<Product>> getAllOrderItemsByAccountId(@RequestParam String username) {
        int accountId = accountService.getAccountId(username);
        List<OrderItem> orderItems = orderItemService.getAllOrderItemsByAccountId(accountId);
        List<Product> products = new ArrayList<>();
        for (OrderItem item : orderItems) {
            Product product = productService.findProductById(item.getProductId());
            products.add(product);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/orderItem/{username}/{productId}")
    public ResponseEntity<OrderItem> addProductToOrder(@PathVariable String username, @PathVariable int productId) {
        int accountId = accountService.getAccountId(username);
        return new ResponseEntity<>(orderItemService.addProductToOrders(accountId, productId), HttpStatus.CREATED);
    }
}
