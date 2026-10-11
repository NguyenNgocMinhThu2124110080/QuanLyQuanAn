package com.example.demo.repository;
import com.example.demo.entity.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Repository
public interface PhanQuyenRepository extends JpaRepository<PhanQuyen, Integer> {
}
