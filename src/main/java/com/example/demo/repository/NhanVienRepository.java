package com.example.demo.repository;

import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
public interface NhanVienRepository extends JpaRepository<NhanVien, Integer> {
    Optional<NhanVien> findByNguoiDung(NguoiDung nguoiDung);
    List<NhanVien> findByChucVu(String chucVu);
}
