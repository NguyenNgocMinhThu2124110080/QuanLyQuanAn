package com.example.demo.repository;

import com.example.demo.entity.BanAn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
public interface BanAnRepository extends JpaRepository<BanAn, Integer> {
    List<BanAn> findByTrangThai(String trangThai);
}
