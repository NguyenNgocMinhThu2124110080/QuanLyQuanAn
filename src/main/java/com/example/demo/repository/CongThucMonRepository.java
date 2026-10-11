package com.example.demo.repository;

import com.example.demo.entity.CongThucMon;
import com.example.demo.entity.CongThucMonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Repository
public interface CongThucMonRepository extends JpaRepository<CongThucMon, CongThucMonId> {
    List<CongThucMon> findById_MaMon(Integer maMon);
}
