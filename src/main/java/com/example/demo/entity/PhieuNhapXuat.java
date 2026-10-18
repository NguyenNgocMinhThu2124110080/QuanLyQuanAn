package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "phieu_nhap_xuat")
@Data
public class PhieuNhapXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pn_id")
    private Integer pnId;

    @Column(name = "loai_phieu")
    private String loaiPhieu;

    @Column(name = "ngay_nhap_xuat")
    private LocalDateTime ngayNhapXuat;

    @Column(name = "so_luong")
    private BigDecimal soLuong;

    @Column(name = "don_gia")
    private BigDecimal donGia;

    @Column(name = "tong_tien")
    private BigDecimal tongTien;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name="nl_id")
    private NguyenLieu nguyenLieu;

    @ManyToOne
    @JoinColumn(name="ncc_id")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name="nv_id")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name="kho_id")
    private Kho kho;

    public PhieuNhapXuat() {
    }

    public Integer getPnId() {
        return pnId;
    }

    public void setPnId(Integer pnId) {
        this.pnId = pnId;
    }

    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
    }

    public LocalDateTime getNgayNhapXuat() {
        return ngayNhapXuat;
    }

    public void setNgayNhapXuat(LocalDateTime ngayNhapXuat) {
        this.ngayNhapXuat = ngayNhapXuat;
    }

    public BigDecimal getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(BigDecimal soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getDonGia() {
        return donGia;
    }

    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    public BigDecimal getTongTien() {
        return tongTien;
    }

    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public NguyenLieu getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Kho getKho() {
        return kho;
    }

    public void setKho(Kho kho) {
        this.kho = kho;
    }
}
