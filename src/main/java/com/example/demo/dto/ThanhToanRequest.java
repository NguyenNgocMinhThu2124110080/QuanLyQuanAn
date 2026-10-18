package com.example.demo.dto;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO yêu cầu thanh toán đơn hàng
public class ThanhToanRequest {
    private Integer maDon;
    private String phuongThuc; // TIEN_MAT, CHUYEN_KHOAN, THE
    private Integer maNv;

    public ThanhToanRequest() {
    }

    public ThanhToanRequest(Integer maDon, String phuongThuc, Integer maNv) {
        this.maDon = maDon;
        this.phuongThuc = phuongThuc;
        this.maNv = maNv;
    }

    public Integer getMaDon() {
        return maDon;
    }

    public void setMaDon(Integer maDon) {
        this.maDon = maDon;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public Integer getMaNv() {
        return maNv;
    }

    public void setMaNv(Integer maNv) {
        this.maNv = maNv;
    }
}
