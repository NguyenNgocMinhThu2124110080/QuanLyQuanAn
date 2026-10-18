package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: Äáº¶NG VÅ¨ THANH PHÃT
// Chá»‹u trÃ¡ch nhiá»‡m báº£o máº­t vÃ  quáº£n lÃ½ tÃ i khoáº£n.
@Entity
@Table(name = "tai_khoan")
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

    public Integer getTaiKhoanId() {
        return taiKhoanId;
    }

    public void setTaiKhoanId(Integer taiKhoanId) {
        this.taiKhoanId = taiKhoanId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhanQuyen getPhanQuyen() {
        return phanQuyen;
    }

    public void setPhanQuyen(PhanQuyen phanQuyen) {
        this.phanQuyen = phanQuyen;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}

