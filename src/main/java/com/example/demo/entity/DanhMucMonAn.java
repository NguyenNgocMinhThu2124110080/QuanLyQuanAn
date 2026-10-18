package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: NGUYá»„N XUÃ‚N QUÃ
@Entity
@Table(name = "danh_muc_mon_an")
public class DanhMucMonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_danhmuc")
    private Integer maDanhmuc;

    @Column(name = "ten_danhmuc")
    private String tenDanhmuc;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    public DanhMucMonAn() {
    }

    public Integer getMaDanhmuc() {
        return maDanhmuc;
    }

    public void setMaDanhmuc(Integer maDanhmuc) {
        this.maDanhmuc = maDanhmuc;
    }

    public String getTenDanhmuc() {
        return tenDanhmuc;
    }

    public void setTenDanhmuc(String tenDanhmuc) {
        this.tenDanhmuc = tenDanhmuc;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}

