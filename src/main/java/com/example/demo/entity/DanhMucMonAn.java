package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Entity
@Table(name = "danh_muc_mon_an")
@Data
public class DanhMucMonAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maDanhmuc")
    private Integer maDanhmuc;
    @Column(name = "ten_danhmuc")
    private String tenDanhmuc;
    @Column(name = "mo_ta")
    private String moTa;
    @Column(name = "trang_thai")
    private Integer trangThai;
}
