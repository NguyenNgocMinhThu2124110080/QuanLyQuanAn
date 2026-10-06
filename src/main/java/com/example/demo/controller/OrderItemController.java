package com.example.demo.controller;

import com.example.demo.model.OrderItem;
import com.example.demo.service.OrderItemService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/order-items")
public class OrderItemController {
    private final OrderItemService service;

    public OrderItemController(OrderItemService service) {
        this.service = service;
    }

    @GetMapping
    public List<OrderItem> getAllOrderItems() { return service.getAllOrderItems(); }

    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable int id) {
        return service.getOrderItemById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy chi tiết đơn hàng có id=" + id));
    }

    @PostMapping
    public String addOrderItem(@RequestBody OrderItem orderItem) {
        service.addOrderItem(orderItem);
        return "Order item added successfully!";
    }

    @PutMapping("/{id}")
    public OrderItem updateOrderItem(@PathVariable int id, @RequestBody OrderItem orderItem) {
        return service.updateOrderItem(id, orderItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteOrderItem(@PathVariable int id) {
        service.deleteOrderItem(id);
        return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
    }
}
