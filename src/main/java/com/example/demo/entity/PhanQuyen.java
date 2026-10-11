package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Entity
@Table(name = "phan_quyen")
@Data
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pqId")
    private Integer pqId;
    @Column(name = "ten_pq")
    private String tenPq;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "trang_thai")
    private Integer trangThai;
}
