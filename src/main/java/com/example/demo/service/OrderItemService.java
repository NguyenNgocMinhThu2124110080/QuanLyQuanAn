package com.example.demo.service;

import com.example.demo.model.OrderItem;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.OrderItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {
    private final OrderItemRepository repository;

    public OrderItemService(OrderItemRepository repository) {
        this.repository = repository;
    }

    public List<OrderItem> getAllOrderItems() {
        return repository.findAll();
    }

    public Optional<OrderItem> getOrderItemById(int id) {
        return repository.findById(id);
    }

    public OrderItem addOrderItem(OrderItem orderItem) {
        orderItem.setId(null);
        return repository.save(orderItem);
    }

    public OrderItem updateOrderItem(int id, OrderItem orderItem) {
        return repository.findById(id).map(existing -> {
            existing.setOrderId(orderItem.getOrderId());
            existing.setProductId(orderItem.getProductId());
            existing.setQuantity(orderItem.getQuantity());
            existing.setUnitPrice(orderItem.getUnitPrice());
            existing.setCookingStatus(orderItem.getCookingStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chi tiết đơn hàng có id=" + id));
    }

    public void deleteOrderItem(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy chi tiết đơn hàng có id=" + id);
        }
        repository.deleteById(id);
    }
}
