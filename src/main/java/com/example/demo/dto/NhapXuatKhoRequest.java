package com.example.demo.dto;

import lombok.Data;
import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// DTO yêu cầu Nhập kho hoặc Xuất kho nguyên liệu
@Data
public class NhapXuatKhoRequest {
    private String loaiPhieu;    // "NHAP" hoặc "XUAT"
    private Integer nlId;        // Mã nguyên liệu
    private BigDecimal soLuong;  // Số lượng (kg, lít, gói...)
    private BigDecimal donGia;   // Đơn giá nhập
    private Integer khoId;       // Mã kho lưu trữ
    private Integer nccId;       // Mã nhà cung cấp (bắt buộc khi nhập)
    private Integer nvId;        // Mã nhân viên thủ kho lập phiếu
    private String ghiChu;       // Ghi chú lý do xuất hoặc nguồn nhập
}
