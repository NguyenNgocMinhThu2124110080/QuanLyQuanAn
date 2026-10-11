package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// DTO báo cáo tình hình tồn kho và cảnh báo thiếu nguyên liệu
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NguyenLieuTonKhoResponse {
    private Integer nlId;
    private String tenNguyenLieu;
    private String donViTinh;
    private BigDecimal soLuongTon;
    private BigDecimal giaNhap;
    private String tenNhaCungCap;
    private String mucDoCanhBao; // "AN_TOAN", "SAP_HET", "HET_HANG"
}
