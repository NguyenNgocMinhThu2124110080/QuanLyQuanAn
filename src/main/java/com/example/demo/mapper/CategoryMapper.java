package com.example.demo.mapper;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.model.Category;
import org.springframework.stereotype.Component;

/**
 * Mapper chuyển đổi giữa Category entity và DTO.
 * - toEntity: CategoryRequest → Category (dùng khi lưu vào DB)
 * - toResponse: Category → CategoryResponse (dùng khi trả ra client)
 */
@Component
public class CategoryMapper {

    /** Chuyển CategoryRequest (DTO input) → Category (entity để lưu DB) */
    public Category toEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return category;
    }

    /** Chuyển Category (entity từ DB) → CategoryResponse (DTO output trả client) */
    public CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
