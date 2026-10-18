package com.example.demo.dto;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO yêu cầu thêm món ăn vào đơn hàng (gọi món)
public class ThemMonVaoDonRequest {
    private Integer maDon;
    private Integer maMon;
    private Integer soLuong;

    public ThemMonVaoDonRequest() {
    }

    public ThemMonVaoDonRequest(Integer maDon, Integer maMon, Integer soLuong) {
        this.maDon = maDon;
        this.maMon = maMon;
        this.soLuong = soLuong;
    }

    public Integer getMaDon() {
        return maDon;
    }

    public void setMaDon(Integer maDon) {
        this.maDon = maDon;
    }

    public Integer getMaMon() {
        return maMon;
    }

    public void setMaMon(Integer maMon) {
        this.maMon = maMon;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }
}
