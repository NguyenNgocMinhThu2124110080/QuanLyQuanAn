package com.example.demo.dto;

import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// DTO báo cáo tình hình tồn kho và cảnh báo thiếu nguyên liệu
public class NguyenLieuTonKhoResponse {
    private Integer nlId;
    private String tenNguyenLieu;
    private String donViTinh;
    private BigDecimal soLuongTon;
    private BigDecimal giaNhap;
    private String tenNhaCungCap;
    private String mucDoCanhBao; // "AN_TOAN", "SAP_HET", "HET_HANG"

    public NguyenLieuTonKhoResponse() {
    }

    public NguyenLieuTonKhoResponse(Integer nlId, String tenNguyenLieu, String donViTinh,
                                    BigDecimal soLuongTon, BigDecimal giaNhap,
                                    String tenNhaCungCap, String mucDoCanhBao) {
        this.nlId = nlId;
        this.tenNguyenLieu = tenNguyenLieu;
        this.donViTinh = donViTinh;
        this.soLuongTon = soLuongTon;
        this.giaNhap = giaNhap;
        this.tenNhaCungCap = tenNhaCungCap;
        this.mucDoCanhBao = mucDoCanhBao;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer nlId;
        private String tenNguyenLieu;
        private String donViTinh;
        private BigDecimal soLuongTon;
        private BigDecimal giaNhap;
        private String tenNhaCungCap;
        private String mucDoCanhBao;

        public Builder nlId(Integer val) { this.nlId = val; return this; }
        public Builder tenNguyenLieu(String val) { this.tenNguyenLieu = val; return this; }
        public Builder donViTinh(String val) { this.donViTinh = val; return this; }
        public Builder soLuongTon(BigDecimal val) { this.soLuongTon = val; return this; }
        public Builder giaNhap(BigDecimal val) { this.giaNhap = val; return this; }
        public Builder tenNhaCungCap(String val) { this.tenNhaCungCap = val; return this; }
        public Builder mucDoCanhBao(String val) { this.mucDoCanhBao = val; return this; }

        public NguyenLieuTonKhoResponse build() {
            return new NguyenLieuTonKhoResponse(nlId, tenNguyenLieu, donViTinh, soLuongTon, giaNhap, tenNhaCungCap, mucDoCanhBao);
        }
    }

    public Integer getNlId() { return nlId; }
    public void setNlId(Integer nlId) { this.nlId = nlId; }

    public String getTenNguyenLieu() { return tenNguyenLieu; }
    public void setTenNguyenLieu(String tenNguyenLieu) { this.tenNguyenLieu = tenNguyenLieu; }

    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }

    public BigDecimal getSoLuongTon() { return soLuongTon; }
    public void setSoLuongTon(BigDecimal soLuongTon) { this.soLuongTon = soLuongTon; }

    public BigDecimal getGiaNhap() { return giaNhap; }
    public void setGiaNhap(BigDecimal giaNhap) { this.giaNhap = giaNhap; }

    public String getTenNhaCungCap() { return tenNhaCungCap; }
    public void setTenNhaCungCap(String tenNhaCungCap) { this.tenNhaCungCap = tenNhaCungCap; }

    public String getMucDoCanhBao() { return mucDoCanhBao; }
    public void setMucDoCanhBao(String mucDoCanhBao) { this.mucDoCanhBao = mucDoCanhBao; }
}
