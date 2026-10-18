package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// PHáº¦N Cá»¦A: Äáº¶NG VÅ¨ THANH PHÃT
@Entity
@Table(name = "thanh_toan")
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_tt")
    private Integer maTt;

    @Column(name = "phuong_thuc")
    private String phuongThuc;

    @Column(name = "so_tien")
    private BigDecimal soTien;

    @Column(name = "thoi_gian")
    private LocalDateTime thoiGian;

    @Column(name = "trang_thai")
    private String trangThai;

    @OneToOne
    @JoinColumn(name="ma_don")
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name="ma_nv")
    private NhanVien nhanVien;

    public ThanhToan() {
    }

    public Integer getMaTt() {
        return maTt;
    }

    public void setMaTt(Integer maTt) {
        this.maTt = maTt;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public BigDecimal getSoTien() {
        return soTien;
    }

    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    public LocalDateTime getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(LocalDateTime thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public DonHang getDonHang() {
        return donHang;
    }

    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
}

