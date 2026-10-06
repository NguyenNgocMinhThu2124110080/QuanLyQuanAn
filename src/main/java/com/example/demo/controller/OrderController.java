package com.example.demo.controller;

import com.example.demo.dto.OrderRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * OrderController — RESTful API cho Order (đơn hàng).
 * Dùng JPA thông qua OrderService.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    /** GET /api/orders */
    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    /** GET /api/orders/{id} */
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable int id) {
        return service.getOrderById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng có id=" + id));
    }

    /** POST /api/orders */
    @PostMapping
    public ResponseEntity<Order> addOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addOrder(request));
    }

    /** PUT /api/orders/{id} */
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable int id, @Valid @RequestBody OrderRequest request) {
        return service.updateOrder(id, request);
    }

    /** DELETE /api/orders/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteOrder(@PathVariable int id) {
        service.deleteOrder(id);
        return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
    }
}
