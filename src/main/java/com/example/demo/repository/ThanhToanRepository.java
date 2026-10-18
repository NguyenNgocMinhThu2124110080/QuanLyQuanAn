package com.example.demo.repository;

import com.example.demo.entity.ThanhToan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Repository
public interface ThanhToanRepository extends JpaRepository<ThanhToan, Integer> {
    Optional<ThanhToan> findByDonHang_MaDon(Integer maDon);
    List<ThanhToan> findByThoiGianBetween(LocalDateTime batDau, LocalDateTime ketThuc);
}
