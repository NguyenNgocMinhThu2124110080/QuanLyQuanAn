package com.example.demo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO hiển thị toàn bộ hoá đơn bao gồm danh sách món ăn đã gọi
public class DonHangChiTietResponse {
    private Integer maDon;
    private String tenBan;
    private String tenNhanVien;
    private LocalDateTime ngayDat;
    private String trangThai;
    private BigDecimal tongTien;
    private List<MonItemResponse> danhSachMon;

    public DonHangChiTietResponse() {
    }

    public DonHangChiTietResponse(Integer maDon, String tenBan, String tenNhanVien,
                                  LocalDateTime ngayDat, String trangThai, BigDecimal tongTien,
                                  List<MonItemResponse> danhSachMon) {
        this.maDon = maDon;
        this.tenBan = tenBan;
        this.tenNhanVien = tenNhanVien;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.danhSachMon = danhSachMon;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer maDon;
        private String tenBan;
        private String tenNhanVien;
        private LocalDateTime ngayDat;
        private String trangThai;
        private BigDecimal tongTien;
        private List<MonItemResponse> danhSachMon;

        public Builder maDon(Integer val) { this.maDon = val; return this; }
        public Builder tenBan(String val) { this.tenBan = val; return this; }
        public Builder tenNhanVien(String val) { this.tenNhanVien = val; return this; }
        public Builder ngayDat(LocalDateTime val) { this.ngayDat = val; return this; }
        public Builder trangThai(String val) { this.trangThai = val; return this; }
        public Builder tongTien(BigDecimal val) { this.tongTien = val; return this; }
        public Builder danhSachMon(List<MonItemResponse> val) { this.danhSachMon = val; return this; }

        public DonHangChiTietResponse build() {
            return new DonHangChiTietResponse(maDon, tenBan, tenNhanVien, ngayDat, trangThai, tongTien, danhSachMon);
        }
    }

    public Integer getMaDon() { return maDon; }
    public void setMaDon(Integer maDon) { this.maDon = maDon; }

    public String getTenBan() { return tenBan; }
    public void setTenBan(String tenBan) { this.tenBan = tenBan; }

    public String getTenNhanVien() { return tenNhanVien; }
    public void setTenNhanVien(String tenNhanVien) { this.tenNhanVien = tenNhanVien; }

    public LocalDateTime getNgayDat() { return ngayDat; }
    public void setNgayDat(LocalDateTime ngayDat) { this.ngayDat = ngayDat; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }

    public BigDecimal getTongTien() { return tongTien; }
    public void setTongTien(BigDecimal tongTien) { this.tongTien = tongTien; }

    public List<MonItemResponse> getDanhSachMon() { return danhSachMon; }
    public void setDanhSachMon(List<MonItemResponse> danhSachMon) { this.danhSachMon = danhSachMon; }

    public static class MonItemResponse {
        private Integer maCtdh;
        private Integer maMon;
        private String tenMon;
        private Integer soLuong;
        private BigDecimal donGia;
        private BigDecimal thanhTien;

        public MonItemResponse() {
        }

        public MonItemResponse(Integer maCtdh, Integer maMon, String tenMon,
                               Integer soLuong, BigDecimal donGia, BigDecimal thanhTien) {
            this.maCtdh = maCtdh;
            this.maMon = maMon;
            this.tenMon = tenMon;
            this.soLuong = soLuong;
            this.donGia = donGia;
            this.thanhTien = thanhTien;
        }

        public static MonItemBuilder builder() {
            return new MonItemBuilder();
        }

        public static class MonItemBuilder {
            private Integer maCtdh;
            private Integer maMon;
            private String tenMon;
            private Integer soLuong;
            private BigDecimal donGia;
            private BigDecimal thanhTien;

            public MonItemBuilder maCtdh(Integer val) { this.maCtdh = val; return this; }
            public MonItemBuilder maMon(Integer val) { this.maMon = val; return this; }
            public MonItemBuilder tenMon(String val) { this.tenMon = val; return this; }
            public MonItemBuilder soLuong(Integer val) { this.soLuong = val; return this; }
            public MonItemBuilder donGia(BigDecimal val) { this.donGia = val; return this; }
            public MonItemBuilder thanhTien(BigDecimal val) { this.thanhTien = val; return this; }

            public MonItemResponse build() {
                return new MonItemResponse(maCtdh, maMon, tenMon, soLuong, donGia, thanhTien);
            }
        }

        public Integer getMaCtdh() { return maCtdh; }
        public void setMaCtdh(Integer maCtdh) { this.maCtdh = maCtdh; }

        public Integer getMaMon() { return maMon; }
        public void setMaMon(Integer maMon) { this.maMon = maMon; }

        public String getTenMon() { return tenMon; }
        public void setTenMon(String tenMon) { this.tenMon = tenMon; }

        public Integer getSoLuong() { return soLuong; }
        public void setSoLuong(Integer soLuong) { this.soLuong = soLuong; }

        public BigDecimal getDonGia() { return donGia; }
        public void setDonGia(BigDecimal donGia) { this.donGia = donGia; }

        public BigDecimal getThanhTien() { return thanhTien; }
        public void setThanhTien(BigDecimal thanhTien) { this.thanhTien = thanhTien; }
    }
}
