package com.example.demo.repository;
import com.example.demo.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Repository
public interface NhaCungCapRepository extends JpaRepository<NhaCungCap, Integer> {
}
