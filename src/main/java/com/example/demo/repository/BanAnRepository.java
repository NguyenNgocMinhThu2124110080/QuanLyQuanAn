package com.example.demo.repository;
import com.example.demo.entity.BanAn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Repository
public interface BanAnRepository extends JpaRepository<BanAn, Integer> {
}
