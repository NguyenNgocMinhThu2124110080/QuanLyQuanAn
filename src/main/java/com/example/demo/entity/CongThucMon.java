package com.example.demo.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

// PHáº¦N Cá»¦A: NGUYá»„N NGá»ŒC MINH THÆ¯
@Entity
@Table(name = "cong_thuc_mon")
public class CongThucMon {
    @EmbeddedId
    private CongThucMonId id;

    @ManyToOne
    @JoinColumn(name="ma_mon", insertable=false, updatable=false)
    private MonAn monAn;

    @ManyToOne
    @JoinColumn(name="nl_id", insertable=false, updatable=false)
    private NguyenLieu nguyenLieu;

    @Column(name = "so_luong_dung")
    private BigDecimal soLuongDung;

    public CongThucMon() {
    }

    public CongThucMonId getId() {
        return id;
    }

    public void setId(CongThucMonId id) {
        this.id = id;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public void setMonAn(MonAn monAn) {
        this.monAn = monAn;
    }

    public NguyenLieu getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(NguyenLieu nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public BigDecimal getSoLuongDung() {
        return soLuongDung;
    }

    public void setSoLuongDung(BigDecimal soLuongDung) {
        this.soLuongDung = soLuongDung;
    }
}

