package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO tạo nhân viên mới cùng lúc với thông tin cá nhân và tài khoản
@Data
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
}
