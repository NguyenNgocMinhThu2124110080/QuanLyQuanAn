package com.example.demo.dto;

import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// DTO yêu cầu Nhập kho hoặc Xuất kho nguyên liệu
public class NhapXuatKhoRequest {
    private String loaiPhieu;    // "NHAP" hoặc "XUAT"
    private Integer nlId;        // Mã nguyên liệu
    private BigDecimal soLuong;  // Số lượng (kg, lít, gói...)
    private BigDecimal donGia;   // Đơn giá nhập
    private Integer khoId;       // Mã kho lưu trữ
    private Integer nccId;       // Mã nhà cung cấp (bắt buộc khi nhập)
    private Integer nvId;        // Mã nhân viên thủ kho lập phiếu
    private String ghiChu;       // Ghi chú lý do xuất hoặc nguồn nhập

    public NhapXuatKhoRequest() {
    }

    public NhapXuatKhoRequest(String loaiPhieu, Integer nlId, BigDecimal soLuong, BigDecimal donGia,
                              Integer khoId, Integer nccId, Integer nvId, String ghiChu) {
        this.loaiPhieu = loaiPhieu;
        this.nlId = nlId;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.khoId = khoId;
        this.nccId = nccId;
        this.nvId = nvId;
        this.ghiChu = ghiChu;
    }

    public String getLoaiPhieu() {
        return loaiPhieu;
    }

    public void setLoaiPhieu(String loaiPhieu) {
        this.loaiPhieu = loaiPhieu;
    }

    public Integer getNlId() {
        return nlId;
    }

    public void setNlId(Integer nlId) {
        this.nlId = nlId;
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

    public Integer getKhoId() {
        return khoId;
    }

    public void setKhoId(Integer khoId) {
        this.khoId = khoId;
    }

    public Integer getNccId() {
        return nccId;
    }

    public void setNccId(Integer nccId) {
        this.nccId = nccId;
    }

    public Integer getNvId() {
        return nvId;
    }

    public void setNvId(Integer nvId) {
        this.nvId = nvId;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
