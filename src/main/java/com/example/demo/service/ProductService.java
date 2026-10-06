package com.example.demo.service;

import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.ProductResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductJdbcRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductJdbcRepository jdbcRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;

    public ProductService(ProductRepository repository,
                          ProductJdbcRepository jdbcRepository,
                          CategoryRepository categoryRepository,
                          ProductMapper mapper) {
        this.repository = repository;
        this.jdbcRepository = jdbcRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    /** Lấy tất cả sản phẩm — JPA */
    public List<ProductResponse> getAllProducts() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    /** Lấy sản phẩm theo ID — JPA */
    public ProductResponse getProductById(int id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm có id=" + id));
        return toResponse(product);
    }

    /** Thêm sản phẩm mới — JPA. Validate categoryId tồn tại trước khi lưu */
    public ProductResponse addProduct(ProductRequest request) {
        // Kiểm tra categoryId có tồn tại không → ResourceNotFoundException nếu không có
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục có id=" + request.getCategoryId()));
        Product product = mapper.toEntity(request);
        product.setId(null);
        Product saved = repository.save(product);
        return mapper.toResponse(saved, category);
    }

    /** Cập nhật sản phẩm — JPA */
    public ProductResponse updateProduct(int id, ProductRequest request) {
        Product existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sản phẩm có id=" + id));
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Không tìm thấy danh mục có id=" + request.getCategoryId()));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setQuantity(request.getQuantity());
        existing.setCategoryId(request.getCategoryId());
        return mapper.toResponse(repository.save(existing), category);
    }

    /** Xóa sản phẩm — JPA */
    public void deleteProduct(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy sản phẩm có id=" + id);
        }
        repository.deleteById(id);
    }

    /** Tìm sản phẩm theo tên — JPA Derived Query */
    public List<ProductResponse> searchByName(String keyword) {
        return repository.findByNameContainingIgnoreCase(keyword).stream()
                .map(this::toResponse)
                .toList();
    }

    /** Tìm sản phẩm theo categoryId — JPA Derived Query */
    public List<ProductResponse> getByCategory(int categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new ResourceNotFoundException("Không tìm thấy danh mục có id=" + categoryId);
        }
        return repository.findByCategoryId(categoryId).stream()
                .map(this::toResponse)
                .toList();
    }

    /** Tìm sản phẩm tồn kho thấp — JDBC (truy vấn SQL thuần) */
    public List<Product> findLowStock(int threshold) {
        if (threshold < 0) {
            throw new IllegalArgumentException("Ngưỡng tồn kho phải >= 0");
        }
        return jdbcRepository.findLowStock(threshold);
    }

    /** Helper: Product → ProductResponse (kèm ánh xạ category) */
    private ProductResponse toResponse(Product product) {
        Category category = categoryRepository.findById(product.getCategoryId()).orElse(null);
        return mapper.toResponse(product, category);
    }
}
