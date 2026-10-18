package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 4: DTO Thống kê Nhập / Xuất kho
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThongKeKhoResponse {
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private Long tongSoPhieuNhap;
    private Long tongSoPhieuXuat;
    private BigDecimal tongTienNhap;
    private BigDecimal tongTienXuat;
    private Long soNguyenLieuSapHet;
}
