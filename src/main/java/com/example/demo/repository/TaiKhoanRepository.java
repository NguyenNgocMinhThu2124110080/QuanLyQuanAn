package com.example.demo.repository;

import com.example.demo.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Repository
public interface TaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    Optional<TaiKhoan> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
