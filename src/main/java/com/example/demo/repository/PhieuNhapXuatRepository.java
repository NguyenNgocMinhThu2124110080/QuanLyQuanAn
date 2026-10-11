package com.example.demo.repository;
import com.example.demo.entity.PhieuNhapXuat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Repository
public interface PhieuNhapXuatRepository extends JpaRepository<PhieuNhapXuat, Integer> {
}
