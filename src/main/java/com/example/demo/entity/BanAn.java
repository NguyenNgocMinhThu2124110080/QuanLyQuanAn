package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: NGUYá»„N XUÃ‚N QUÃ
@Entity
@Table(name = "ban_an")
public class BanAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ban")
    private Integer maBan;

    @Column(name = "ten_ban")
    private String tenBan;

    @Column(name = "so_cho_ngoi")
    private Integer soChoNgoi;

    @Column(name = "trang_thai")
    private String trangThai;

    public BanAn() {
    }

    public Integer getMaBan() {
        return maBan;
    }

    public void setMaBan(Integer maBan) {
        this.maBan = maBan;
    }

    public String getTenBan() {
        return tenBan;
    }

    public void setTenBan(String tenBan) {
        this.tenBan = tenBan;
    }

    public Integer getSoChoNgoi() {
        return soChoNgoi;
    }

    public void setSoChoNgoi(Integer soChoNgoi) {
        this.soChoNgoi = soChoNgoi;
    }

    public Integer getSucChua() {
        return soChoNgoi;
    }

    public void setSucChua(Integer sucChua) {
        if (this.soChoNgoi == null) {
            this.soChoNgoi = sucChua;
        }
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}

