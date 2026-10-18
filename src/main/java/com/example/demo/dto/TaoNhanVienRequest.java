package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO tạo nhân viên mới cùng lúc với thông tin cá nhân và tài khoản
public class TaoNhanVienRequest {
    // Thông tin người dùng
    private String hoTen;
    private String soDienThoai;
    private String diaChi;
    private String gioiTinh;
    private LocalDate ngaySinh;

    // Thông tin nhân viên
    private String chucVu;
    private BigDecimal luong;

    // Thông tin tài khoản
    private String username;
    private String password;
    private String email;
    private Integer pqId;

    public TaoNhanVienRequest() {
    }

    public TaoNhanVienRequest(String hoTen, String soDienThoai, String diaChi, String gioiTinh, LocalDate ngaySinh,
                              String chucVu, BigDecimal luong, String username, String password, String email, Integer pqId) {
        this.hoTen = hoTen;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.chucVu = chucVu;
        this.luong = luong;
        this.username = username;
        this.password = password;
        this.email = email;
        this.pqId = pqId;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public BigDecimal getLuong() {
        return luong;
    }

    public void setLuong(BigDecimal luong) {
        this.luong = luong;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPqId() {
        return pqId;
    }

    public void setPqId(Integer pqId) {
        this.pqId = pqId;
    }
}
