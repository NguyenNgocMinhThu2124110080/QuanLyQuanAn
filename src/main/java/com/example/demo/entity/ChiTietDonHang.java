package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

// PHáº¦N Cá»¦A: NGUYá»„N XUÃ‚N QUÃ
@Entity
@Table(name = "chi_tiet_don_hang")
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_ctdh")
    private Integer maCtdh;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "thanh_tien")
    private BigDecimal thanhTien;

    @ManyToOne
    @JoinColumn(name="ma_don")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name="ma_mon")
    private MonAn monAn;

    public ChiTietDonHang() {
    }

    public Integer getMaCtdh() {
        return maCtdh;
    }

    public void setMaCtdh(Integer maCtdh) {
        this.maCtdh = maCtdh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }
}

