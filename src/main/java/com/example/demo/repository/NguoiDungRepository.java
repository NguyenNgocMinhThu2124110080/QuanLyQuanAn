package com.example.demo.repository;

import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
    Optional<NguoiDung> findByTaiKhoan(TaiKhoan taiKhoan);
    Optional<NguoiDung> findBySoDienThoai(String soDienThoai);
}
