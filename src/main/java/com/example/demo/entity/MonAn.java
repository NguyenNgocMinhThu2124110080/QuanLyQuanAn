package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Entity
@Table(name = "mon_an")
@Data
public class MonAn {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_mon")
    private Integer maMon;

    @Column(name = "ten_mon")
    private String tenMon;

    private BigDecimal gia;

    @Column(name = "hinh_anh")
    private String hinhAnh;

    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "ma_danhmuc")
    private DanhMucMonAn danhMuc;
}
