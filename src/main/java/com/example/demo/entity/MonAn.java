package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

// PHáº¦N Cá»¦A: NGUYá»„N XUÃ‚N QUÃ
@Entity
@Table(name = "mon_an")
public class MonAn {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mon")
    private Integer maMon;

    @Column(name = "ten_mon")
    private String tenMon;

    private BigDecimal gia;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_danhmuc")
    private DanhMucMonAn danhMuc;

    public MonAn() {
    }

    public Integer getMaMon() {
        return maMon;
    }

    public void setMaMon(Integer maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
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

    public DanhMucMonAn getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMucMonAn danhMuc) {
        this.danhMuc = danhMuc;
    }
}

