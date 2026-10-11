package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Chịu trách nhiệm bảo mật và quản lý tài khoản.
@Entity
@Table(name = "tai_khoan")
@Data
public class TaiKhoan {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tai_khoan_id")
    private Integer taiKhoanId;

    private String username;
    private String password;
    private String email;

    @ManyToOne
    @JoinColumn(name = "pq_id")
    private PhanQuyen phanQuyen;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
