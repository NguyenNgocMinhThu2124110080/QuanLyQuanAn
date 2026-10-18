package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "nguyen_lieu")
@Data
public class NguyenLieu {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nl_id")
    private Integer nlId;

    @Column(name = "ten_nguyen_lieu")
    private String tenNguyenLieu;

    @Column(name = "don_vi_tinh")
    private String donViTinh;

    @Column(name = "gia_nhap")
    private BigDecimal giaNhap;

    @Column(name = "so_luong_ton")
    private BigDecimal soLuongTon;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "ncc_id")
    private NhaCungCap nhaCungCap;

    public NguyenLieu() {
    }

    public Integer getNlId() {
        return nlId;
    }

    public void setNlId(Integer nlId) {
        this.nlId = nlId;
    }

    public String getTenNguyenLieu() {
        return tenNguyenLieu;
    }

    public void setTenNguyenLieu(String tenNguyenLieu) {
        this.tenNguyenLieu = tenNguyenLieu;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }

    public BigDecimal getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(BigDecimal giaNhap) {
        this.giaNhap = giaNhap;
    }

    public BigDecimal getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(BigDecimal soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }
}
