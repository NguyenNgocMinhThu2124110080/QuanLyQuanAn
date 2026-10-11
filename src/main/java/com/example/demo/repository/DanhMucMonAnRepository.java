package com.example.demo.repository;
import com.example.demo.entity.DanhMucMonAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Repository
public interface DanhMucMonAnRepository extends JpaRepository<DanhMucMonAn, Integer> {
}
