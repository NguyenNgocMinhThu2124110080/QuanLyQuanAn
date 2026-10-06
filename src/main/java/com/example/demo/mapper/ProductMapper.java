package com.example.demo.mapper;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

/**
 * Mapper chuyển đổi giữa Product entity và DTO.
 * Giúp ánh xạ dữ liệu giữa 2 bảng: products + categories.
 * - toEntity: ProductRequest → Product
 * - toResponse: Product + Category → ProductResponse (kèm categoryName)
 */
@Component
public class ProductMapper {

    /** Chuyển ProductRequest (DTO input) → Product (entity để lưu DB) */
    public Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategoryId(request.getCategoryId());
        return product;
    }

    /**
     * Chuyển Product (entity từ DB) + Category (entity từ DB) → ProductResponse (DTO output).
     * Đây là bước ánh xạ 2 bảng: lấy categoryName từ bảng categories
     * rồi gắn vào ProductResponse để trả về client.
     */
    public ProductResponse toResponse(Product product, Category category) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategoryId(),
                category != null ? category.getName() : "Không xác định"
        );
    }
}
