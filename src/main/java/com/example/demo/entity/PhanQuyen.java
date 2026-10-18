package com.example.demo.entity;

import jakarta.persistence.*;

// PHáº¦N Cá»¦A: Äáº¶NG VÅ¨ THANH PHÃT
@Entity
@Table(name = "phan_quyen")
public class PhanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pq_id")
    private Integer pqId;

    @Column(name = "ten_pq")
    private String tenPq;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    public Integer getPqId() {
        return pqId;
    }

    public void setPqId(Integer pqId) {
        this.pqId = pqId;
    }

    public String getTenPq() {
        return tenPq;
    }

    public void setTenPq(String tenPq) {
        this.tenPq = tenPq;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
}

