package com.example.demo.repository;

import com.example.demo.entity.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Repository
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu, Integer> {
    List<NguyenLieu> findByTenNguyenLieuContainingIgnoreCase(String ten);
    List<NguyenLieu> findByNhaCungCap_NccId(Integer nccId);
    List<NguyenLieu> findBySoLuongTonLessThanEqual(BigDecimal nguong);
}
