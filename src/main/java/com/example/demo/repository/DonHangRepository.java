package com.example.demo.repository;

import com.example.demo.entity.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Repository
public interface DonHangRepository extends JpaRepository<DonHang, Integer> {
    List<DonHang> findByTrangThai(String trangThai);
    List<DonHang> findByBan_MaBan(Integer maBan);
}
