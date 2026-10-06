package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO trả dữ liệu Product ra cho client.
 * Bao gồm tên category (ánh xạ từ 2 bảng products + categories).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int categoryId;
    private String categoryName; // ánh xạ từ bảng categories sang
}
