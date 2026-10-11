package com.example.demo.repository;

import com.example.demo.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Repository
public interface MonAnRepository extends JpaRepository<MonAn, Integer> {
    List<MonAn> findByTenMonContainingIgnoreCase(String tenMon);
    List<MonAn> findByDanhMuc_MaDanhmuc(Integer maDanhmuc);
    List<MonAn> findByTrangThai(Integer trangThai);
}
