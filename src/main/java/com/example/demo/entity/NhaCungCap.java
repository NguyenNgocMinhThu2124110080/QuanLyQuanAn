package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: NGUYá»„N NGá»ŒC MINH THÆ¯
@Entity
@Table(name = "nha_cung_cap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncc_id")
    private Integer nccId;

    @Column(name = "ten_ncc")
    private String tenNcc;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    private String email;

    @Column(name = "trang_thai")
    private Integer trangThai;

    public NhaCungCap() {
    }

    public Integer getNccId() {
        return nccId;
    }

    public void setNccId(Integer nccId) {
        this.nccId = nccId;
    }

    public String getTenNcc() {
        return tenNcc;
    }

    public void setTenNcc(String tenNcc) {
        this.tenNcc = tenNcc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}

