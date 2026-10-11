package com.example.demo.repository;
import com.example.demo.entity.CongThucMon;
import com.example.demo.entity.CongThucMonId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Repository
public interface CongThucMonRepository extends JpaRepository<CongThucMon, CongThucMonId> {
}
