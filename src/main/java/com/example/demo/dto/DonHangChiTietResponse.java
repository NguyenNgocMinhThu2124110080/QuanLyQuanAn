package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO hiển thị toàn bộ hoá đơn bao gồm danh sách món ăn đã gọi
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonHangChiTietResponse {
    private Integer maDon;
    private String tenBan;
    private String tenNhanVien;
    private LocalDateTime ngayDat;
    private String trangThai;
    private BigDecimal tongTien;
    private List<MonItemResponse> danhSachMon;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class MonItemResponse {
        private Integer maCtdh;
        private Integer maMon;
        private String tenMon;
        private Integer soLuong;
        private BigDecimal donGia;
        private BigDecimal thanhTien;
    }
}
