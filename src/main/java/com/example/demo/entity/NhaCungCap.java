package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "nha_cung_cap")
@Data
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ncc_id")
    private Integer nccId;
    @Column(name = "ten_ncc")
    private String tenNcc;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    private String email;
    @Column(name = "trang_thai")
    private Integer trangThai;
}
