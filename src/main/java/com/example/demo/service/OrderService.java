package com.example.demo.service;

import com.example.demo.dto.OrderRequest;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.repository.DiningTableRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final DiningTableRepository tableRepository;

    public OrderService(OrderRepository repository, DiningTableRepository tableRepository) {
        this.repository = repository;
        this.tableRepository = tableRepository;
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Optional<Order> getOrderById(int id) {
        return repository.findById(id);
    }

    public Order addOrder(OrderRequest request) {
        // Validate tableId tồn tại
        if (!tableRepository.existsById(request.getTableId())) {
            throw new ResourceNotFoundException("Không tìm thấy bàn ăn có id=" + request.getTableId());
        }
        Order order = new Order();
        order.setTableId(request.getTableId());
        order.setStaffName(request.getStaffName());
        order.setOrderDate(request.getOrderDate() != null ? request.getOrderDate() : LocalDate.now());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus(request.getStatus());
        return repository.save(order);
    }

    public Order updateOrder(int id, OrderRequest request) {
        Order existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy đơn hàng có id=" + id));
        if (!tableRepository.existsById(request.getTableId())) {
            throw new ResourceNotFoundException("Không tìm thấy bàn ăn có id=" + request.getTableId());
        }
        existing.setTableId(request.getTableId());
        existing.setStaffName(request.getStaffName());
        existing.setOrderDate(request.getOrderDate() != null ? request.getOrderDate() : LocalDate.now());
        existing.setTotalAmount(request.getTotalAmount());
        existing.setStatus(request.getStatus());
        return repository.save(existing);
    }

    public void deleteOrder(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy đơn hàng có id=" + id);
        }
        repository.deleteById(id);
    }
}
