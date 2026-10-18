package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 4: DTO Thống kê Nhập / Xuất kho
public class ThongKeKhoResponse {
    private LocalDate tuNgay;
    private LocalDate denNgay;
    private Long tongSoPhieuNhap;
    private Long tongSoPhieuXuat;
    private BigDecimal tongTienNhap;
    private BigDecimal tongTienXuat;
    private Long soNguyenLieuSapHet;

    public ThongKeKhoResponse() {
    }

    public ThongKeKhoResponse(LocalDate tuNgay, LocalDate denNgay, Long tongSoPhieuNhap, Long tongSoPhieuXuat,
                              BigDecimal tongTienNhap, BigDecimal tongTienXuat, Long soNguyenLieuSapHet) {
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.tongSoPhieuNhap = tongSoPhieuNhap;
        this.tongSoPhieuXuat = tongSoPhieuXuat;
        this.tongTienNhap = tongTienNhap;
        this.tongTienXuat = tongTienXuat;
        this.soNguyenLieuSapHet = soNguyenLieuSapHet;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate tuNgay;
        private LocalDate denNgay;
        private Long tongSoPhieuNhap;
        private Long tongSoPhieuXuat;
        private BigDecimal tongTienNhap;
        private BigDecimal tongTienXuat;
        private Long soNguyenLieuSapHet;

        public Builder tuNgay(LocalDate tuNgay) {
            this.tuNgay = tuNgay;
            return this;
        }

        public Builder denNgay(LocalDate denNgay) {
            this.denNgay = denNgay;
            return this;
        }

        public Builder tongSoPhieuNhap(Long tongSoPhieuNhap) {
            this.tongSoPhieuNhap = tongSoPhieuNhap;
            return this;
        }

        public Builder tongSoPhieuXuat(Long tongSoPhieuXuat) {
            this.tongSoPhieuXuat = tongSoPhieuXuat;
            return this;
        }

        public Builder tongTienNhap(BigDecimal tongTienNhap) {
            this.tongTienNhap = tongTienNhap;
            return this;
        }

        public Builder tongTienXuat(BigDecimal tongTienXuat) {
            this.tongTienXuat = tongTienXuat;
            return this;
        }

        public Builder soNguyenLieuSapHet(Long soNguyenLieuSapHet) {
            this.soNguyenLieuSapHet = soNguyenLieuSapHet;
            return this;
        }

        public ThongKeKhoResponse build() {
            return new ThongKeKhoResponse(tuNgay, denNgay, tongSoPhieuNhap, tongSoPhieuXuat,
                    tongTienNhap, tongTienXuat, soNguyenLieuSapHet);
        }
    }

    public LocalDate getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }

    public LocalDate getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(LocalDate denNgay) {
        this.denNgay = denNgay;
    }

    public Long getTongSoPhieuNhap() {
        return tongSoPhieuNhap;
    }

    public void setTongSoPhieuNhap(Long tongSoPhieuNhap) {
        this.tongSoPhieuNhap = tongSoPhieuNhap;
    }

    public Long getTongSoPhieuXuat() {
        return tongSoPhieuXuat;
    }

    public void setTongSoPhieuXuat(Long tongSoPhieuXuat) {
        this.tongSoPhieuXuat = tongSoPhieuXuat;
    }

    public BigDecimal getTongTienNhap() {
        return tongTienNhap;
    }

    public void setTongTienNhap(BigDecimal tongTienNhap) {
        this.tongTienNhap = tongTienNhap;
    }

    public BigDecimal getTongTienXuat() {
        return tongTienXuat;
    }

    public void setTongTienXuat(BigDecimal tongTienXuat) {
        this.tongTienXuat = tongTienXuat;
    }

    public Long getSoNguyenLieuSapHet() {
        return soNguyenLieuSapHet;
    }

    public void setSoNguyenLieuSapHet(Long soNguyenLieuSapHet) {
        this.soNguyenLieuSapHet = soNguyenLieuSapHet;
    }
}
