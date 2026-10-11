package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Entity
@Table(name = "ban_an")
@Data
public class BanAn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maBan")
    private Integer maBan;
    @Column(name = "ten_ban")
    private String tenBan;
    @Column(name = "so_cho_ngoi")
    private Integer soChoNgoi;
    @Column(name = "trang_thai")
    private String trangThai;
}
