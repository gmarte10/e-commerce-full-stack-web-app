package com.giancarlos.service;

import com.giancarlos.model.CartItem;
import com.giancarlos.model.OrderItem;
import com.giancarlos.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItemsByAccountId(int accountId) {
        return orderItemRepository.findByAccountId(accountId);
    }

    public OrderItem addProductToOrders(int accountId, int productId) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductId(productId);
        orderItem.setAccountId(accountId);
        return orderItemRepository.save(orderItem);
    }
}
