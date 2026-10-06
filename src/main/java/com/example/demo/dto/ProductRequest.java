package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * DTO nhận dữ liệu từ client khi tạo/cập nhật Product.
 * Có đầy đủ validation để bắt lỗi khi thiếu tên, giá, số lượng, categoryId.
 */
@Data
public class ProductRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    private String description;

    @NotNull(message = "Giá không được để trống")
    @Positive(message = "Giá phải lớn hơn 0")
    private Double price;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    private Integer quantity;

    @NotNull(message = "CategoryId không được để trống")
    @Positive(message = "CategoryId phải lớn hơn 0")
    private Integer categoryId;
}
