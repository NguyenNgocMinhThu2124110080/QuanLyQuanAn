package com.example.demo.service;

import com.example.demo.dto.CategoryRequest;
import com.example.demo.dto.CategoryResponse;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.model.Category;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final ProductRepository productRepository;
    private final CategoryMapper mapper;

    public CategoryService(CategoryRepository repository,
                           ProductRepository productRepository,
                           CategoryMapper mapper) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public List<CategoryResponse> getAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    public Optional<CategoryResponse> getCategoryById(int id) {
        return repository.findById(id).map(mapper::toResponse);
    }

    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = mapper.toEntity(request);
        category.setId(null);
        return mapper.toResponse(repository.save(category));
    }

    public CategoryResponse updateCategory(int id, CategoryRequest request) {
        Category existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy danh mục có id=" + id));
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        return mapper.toResponse(repository.save(existing));
    }

    public void deleteCategory(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy danh mục có id=" + id);
        }
        // Kiểm tra còn sản phẩm trong category này không
        if (productRepository.existsByCategoryId(id)) {
            throw new IllegalArgumentException(
                    "Không thể xóa danh mục vì còn sản phẩm thuộc danh mục này");
        }
        repository.deleteById(id);
    }

    /** Dùng cho ProductService để validate categoryId */
    public boolean existsById(int id) {
        return repository.existsById(id);
    }

    /** Lấy Category entity (dùng cho Mapper trong ProductService) */
    public Optional<Category> getCategoryEntityById(int id) {
        return repository.findById(id);
    }
}
