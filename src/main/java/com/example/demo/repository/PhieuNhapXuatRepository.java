package com.example.demo.repository;

import com.example.demo.entity.PhieuNhapXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
public interface PhieuNhapXuatRepository extends JpaRepository<PhieuNhapXuat, Integer> {
    List<PhieuNhapXuat> findByNguyenLieu_NlId(Integer nlId);
    List<PhieuNhapXuat> findByLoaiPhieu(String loaiPhieu);
    List<PhieuNhapXuat> findByNgayNhapXuatBetween(LocalDateTime batDau, LocalDateTime ketThuc);
}
