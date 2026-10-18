package com.example.demo.repository;

import com.example.demo.entity.NguyenLieu;
import org.springframework.data.jpa.repository.JpaRepository;
import java.math.BigDecimal;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
public interface NguyenLieuRepository extends JpaRepository<NguyenLieu, Integer> {
    List<NguyenLieu> findByTenNguyenLieuContainingIgnoreCase(String ten);
    List<NguyenLieu> findByNhaCungCap_NccId(Integer nccId);
    List<NguyenLieu> findBySoLuongTonLessThanEqual(BigDecimal nguong);
}
