package com.example.demo.controller;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * CategoryController — RESTful API cho Category.
 * Dùng JPA thông qua CategoryService.
 * Nhận dữ liệu qua CategoryRequest DTO, trả về CategoryResponse DTO.
 */
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    /** GET /api/categories — lấy tất cả danh mục */
    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return service.getAllCategories();
    }

    /** GET /api/categories/{id} — lấy danh mục theo ID */
    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable int id) {
        return service.getCategoryById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục có id=" + id));
    }

    /** POST /api/categories — thêm danh mục mới */
    @PostMapping
    public ResponseEntity<CategoryResponse> addCategory(@Valid @RequestBody CategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCategory(request));
    }

    /** PUT /api/categories/{id} — cập nhật danh mục */
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable int id,
                                           @Valid @RequestBody CategoryRequest request) {
        return service.updateCategory(id, request);
    }

    /** DELETE /api/categories/{id} — xóa danh mục */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteCategory(@PathVariable int id) {
        service.deleteCategory(id);
        return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
    }
}
