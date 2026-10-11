package com.example.demo.dto;

import lombok.Data;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO yêu cầu thanh toán đơn hàng
@Data
public class ThanhToanRequest {
    private Integer maDon;
    private String phuongThuc; // TIEN_MAT, CHUYEN_KHOAN, THE
    private Integer maNv;
}
