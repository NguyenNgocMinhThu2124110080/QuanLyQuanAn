package com.example.demo.service;

import com.example.demo.model.Category;
import com.example.demo.model.DiningTable;
import com.example.demo.model.Order;
import com.example.demo.model.OrderItem;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.DiningTableRepository;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DemoDataInitializer implements CommandLineRunner {
    private final CategoryRepository categoryRepository;
    private final DiningTableRepository diningTableRepository;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;

    public DemoDataInitializer(CategoryRepository categoryRepository,
                               DiningTableRepository diningTableRepository,
                               OrderRepository orderRepository,
                               OrderItemRepository orderItemRepository,
                               ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.diningTableRepository = diningTableRepository;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        Category mainDishes = categoryRepository.findAll().stream().findFirst()
            .orElseGet(() -> categoryRepository.save(new Category(null, "Món chính", "Món ăn chính")));
        Category drinks = categoryRepository.findAll().stream().skip(1).findFirst()
            .orElseGet(() -> categoryRepository.save(new Category(null, "Đồ uống", "Nước uống và trà")));
        DiningTable table = diningTableRepository.findAll().stream().findFirst()
            .orElseGet(() -> diningTableRepository.save(new DiningTable(null, "Bàn 1", 4, "Available")));

        Product rice = productRepository.findAll().stream().findFirst().orElseGet(() -> productRepository.save(
            new Product(null, "Cơm gà nướng", "Cơm, gà nướng và rau ăn kèm", 75000, 50, mainDishes.getId())));
        if (productRepository.count() < 2) {
            productRepository.save(new Product(null, "Trà đào", "Trà đào mật ong", 30000, 8, drinks.getId()));
        }

        Order order = orderRepository.findAll().stream().findFirst().orElseGet(() -> orderRepository.save(
            new Order(null, table.getId(), "Lan", LocalDate.now(), 75000, "Pending")));
        if (orderItemRepository.count() == 0) {
            orderItemRepository.save(new OrderItem(null, order.getId(), rice.getId(), 1, 75000, "Preparing"));
        }
    }
}