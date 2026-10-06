package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public ProductJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> findLowStock(int threshold) {
        return jdbcTemplate.query(
                "SELECT id, name, description, price, quantity, category_id AS categoryId " +
                        "FROM products WHERE quantity <= ? ORDER BY quantity, id",
                new BeanPropertyRowMapper<>(Product.class),
                threshold
        );
    }
}