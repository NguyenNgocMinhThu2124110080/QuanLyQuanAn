package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: DTO báo cáo doanh thu theo ngày
public class DoanhThuResponse {
    private LocalDate ngay;
    private Long tongSoDonThanhToan;
    private BigDecimal tongDoanhThu;
    private BigDecimal tienMat;
    private BigDecimal chuyenKhoan;
    private BigDecimal the;

    public DoanhThuResponse() {
    }

    public DoanhThuResponse(LocalDate ngay, Long tongSoDonThanhToan, BigDecimal tongDoanhThu,
                            BigDecimal tienMat, BigDecimal chuyenKhoan, BigDecimal the) {
        this.ngay = ngay;
        this.tongSoDonThanhToan = tongSoDonThanhToan;
        this.tongDoanhThu = tongDoanhThu;
        this.tienMat = tienMat;
        this.chuyenKhoan = chuyenKhoan;
        this.the = the;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate ngay;
        private Long tongSoDonThanhToan;
        private BigDecimal tongDoanhThu;
        private BigDecimal tienMat;
        private BigDecimal chuyenKhoan;
        private BigDecimal the;

        public Builder ngay(LocalDate ngay) {
            this.ngay = ngay;
            return this;
        }

        public Builder tongSoDonThanhToan(Long tongSoDonThanhToan) {
            this.tongSoDonThanhToan = tongSoDonThanhToan;
            return this;
        }

        public Builder tongDoanhThu(BigDecimal tongDoanhThu) {
            this.tongDoanhThu = tongDoanhThu;
            return this;
        }

        public Builder tienMat(BigDecimal tienMat) {
            this.tienMat = tienMat;
            return this;
        }

        public Builder chuyenKhoan(BigDecimal chuyenKhoan) {
            this.chuyenKhoan = chuyenKhoan;
            return this;
        }

        public Builder the(BigDecimal the) {
            this.the = the;
            return this;
        }

        public DoanhThuResponse build() {
            return new DoanhThuResponse(ngay, tongSoDonThanhToan, tongDoanhThu, tienMat, chuyenKhoan, the);
        }
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public Long getTongSoDonThanhToan() {
        return tongSoDonThanhToan;
    }

    public void setTongSoDonThanhToan(Long tongSoDonThanhToan) {
        this.tongSoDonThanhToan = tongSoDonThanhToan;
    }

    public BigDecimal getTongDoanhThu() {
        return tongDoanhThu;
    }

    public void setTongDoanhThu(BigDecimal tongDoanhThu) {
        this.tongDoanhThu = tongDoanhThu;
    }

    public BigDecimal getTienMat() {
        return tienMat;
    }

    public void setTienMat(BigDecimal tienMat) {
        this.tienMat = tienMat;
    }

    public BigDecimal getChuyenKhoan() {
        return chuyenKhoan;
    }

    public void setChuyenKhoan(BigDecimal chuyenKhoan) {
        this.chuyenKhoan = chuyenKhoan;
    }

    public BigDecimal getThe() {
        return the;
    }

    public void setThe(BigDecimal the) {
        this.the = the;
    }
}
