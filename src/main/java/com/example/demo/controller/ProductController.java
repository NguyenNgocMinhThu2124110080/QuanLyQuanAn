package com.example.demo.controller;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ProductController — RESTful API cho Product.
 * - JPA: CRUD thông thường (thêm/sửa/xóa/xem theo ID, tìm theo tên, theo category)
 * - JDBC: tìm sản phẩm tồn kho thấp (low-stock) bằng SQL thuần
 * Nhận dữ liệu qua ProductRequest DTO (@Valid), trả về ProductResponse DTO.
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    /** GET /api/products — lấy tất cả sản phẩm (JPA) */
    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return service.getAllProducts();
    }

    /** GET /api/products/{id} — lấy sản phẩm theo ID (JPA) */
    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    /** POST /api/products — thêm sản phẩm mới (JPA) */
    @PostMapping
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addProduct(request));
    }

    /** PUT /api/products/{id} — cập nhật sản phẩm (JPA) */
    @PutMapping("/{id}")
    public ProductResponse updateProduct(@PathVariable int id,
                                         @Valid @RequestBody ProductRequest request) {
        return service.updateProduct(id, request);
    }

    /** DELETE /api/products/{id} — xóa sản phẩm (JPA) */
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteProduct(@PathVariable int id) {
        service.deleteProduct(id);
        return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
    }

    /** GET /api/products/search?keyword=... — tìm theo tên (JPA Derived Query) */
    @GetMapping("/search")
    public List<ProductResponse> searchByName(@RequestParam String keyword) {
        return service.searchByName(keyword);
    }

    /** GET /api/products/category/{categoryId} — lấy sản phẩm theo danh mục (JPA) */
    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> getByCategory(@PathVariable int categoryId) {
        return service.getByCategory(categoryId);
    }

    /** GET /api/products/low-stock?threshold=10 — sản phẩm tồn kho thấp (JDBC) */
    @GetMapping("/low-stock")
    public List<Product> getLowStockProducts(@RequestParam(defaultValue = "10") int threshold) {
        return service.findLowStock(threshold);
    }
}
