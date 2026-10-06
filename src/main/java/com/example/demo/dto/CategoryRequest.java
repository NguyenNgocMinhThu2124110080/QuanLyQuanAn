package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * DTO nhận dữ liệu từ client khi tạo/cập nhật Category.
 * Tách biệt dữ liệu input khỏi entity, bảo vệ entity khỏi bị thay đổi trực tiếp.
 */
@Data
public class CategoryRequest {

    @NotBlank(message = "Tên danh mục không được để trống")
    private String name;

    private String description;
}
