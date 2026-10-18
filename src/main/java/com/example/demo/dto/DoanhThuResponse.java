package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: DTO báo cáo doanh thu theo ngày
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoanhThuResponse {
    private LocalDate ngay;
    private Long tongSoDonThanhToan;
    private BigDecimal tongDoanhThu;
    private BigDecimal tienMat;
    private BigDecimal chuyenKhoan;
    private BigDecimal the;
}
