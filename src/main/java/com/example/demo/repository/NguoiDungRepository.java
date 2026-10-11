package com.example.demo.repository;
import com.example.demo.entity.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Repository
public interface NguoiDungRepository extends JpaRepository<NguoiDung, Integer> {
}
