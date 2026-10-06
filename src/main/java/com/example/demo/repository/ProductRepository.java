package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * ProductRepository — sử dụng JPA (Spring Data JPA).
 * JPA dùng cho các thao tác CRUD thông thường, truy vấn theo thuộc tính.
 *
 * Các phương thức:
 * 1. findByCategoryId: tìm tất cả sản phẩm theo categoryId (derived query).
 * 2. findByNameContainingIgnoreCase: tìm sản phẩm theo tên (không phân biệt hoa thường).
 * 3. existsByCategoryId: kiểm tra xem categoryId có tồn tại trong bảng products không.
 * 4. findByPriceBetween: tìm sản phẩm theo khoảng giá.
 * 5. countByCategoryId: đếm số sản phẩm theo categoryId (JPQL).
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /** Tìm tất cả sản phẩm thuộc một category (JPA Derived Query) */
    List<Product> findByCategoryId(int categoryId);

    /** Tìm sản phẩm theo tên, không phân biệt hoa thường (JPA Derived Query) */
    List<Product> findByNameContainingIgnoreCase(String keyword);

    /** Kiểm tra category có sản phẩm nào không (JPA Derived Query) */
    boolean existsByCategoryId(int categoryId);

    /** Tìm sản phẩm theo khoảng giá (JPA Derived Query) */
    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    /** Đếm số sản phẩm theo categoryId (JPQL custom query) */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.categoryId = :categoryId")
    long countByCategoryId(@Param("categoryId") int categoryId);
}
