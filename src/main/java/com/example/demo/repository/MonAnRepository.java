package com.example.demo.repository;

import com.example.demo.entity.MonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
public interface MonAnRepository extends JpaRepository<MonAn, Integer> {
    List<MonAn> findByTenMonContainingIgnoreCase(String tenMon);
    List<MonAn> findByDanhMuc_MaDanhmuc(Integer maDanhmuc);
    List<MonAn> findByTrangThai(Integer trangThai);
}
