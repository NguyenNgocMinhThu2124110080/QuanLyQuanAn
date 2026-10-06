package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO trả dữ liệu Category ra cho client.
 * Che giấu thông tin nội bộ, chỉ trả đúng những gì client cần.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResponse {
    private Integer id;
    private String name;
    private String description;
}
