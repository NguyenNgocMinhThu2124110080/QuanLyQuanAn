package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: NGUYá»„N NGá»ŒC MINH THÆ¯
@Entity
@Table(name = "kho")
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kho_id")
    private Integer khoId;

    @Column(name = "ten_kho")
    private String tenKho;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "trang_thai")
    private Integer trangThai;

    public Kho() {
    }

    public Integer getKhoId() {
        return khoId;
    }

    public void setKhoId(Integer khoId) {
        this.khoId = khoId;
    }

    public String getTenKho() {
        return tenKho;
    }

    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}

