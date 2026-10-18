package com.example.demo.repository;
import com.example.demo.entity.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Integer> {
}
