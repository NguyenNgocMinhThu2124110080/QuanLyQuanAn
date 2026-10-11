package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "nguyen_lieu")
@Data
public class NguyenLieu {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nl_id")
    private Integer nlId;

    @Column(name = "ten_nguyen_lieu")
    private String tenNguyenLieu;

    @Column(name = "don_vi_tinh")
    private String donViTinh;

    @Column(name = "gia_nhap")
    private BigDecimal giaNhap;

    @Column(name = "so_luong_ton")
    private BigDecimal soLuongTon;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "ncc_id")
    private NhaCungCap nhaCungCap;
}
