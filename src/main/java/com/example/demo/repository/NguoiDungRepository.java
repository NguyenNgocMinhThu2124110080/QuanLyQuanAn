package com.example.demo.repository;

import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTaiKhoan(TaiKhoan taiKhoan);
    Optional<NguoiDung> findBySoDienThoai(String soDienThoai);
}
