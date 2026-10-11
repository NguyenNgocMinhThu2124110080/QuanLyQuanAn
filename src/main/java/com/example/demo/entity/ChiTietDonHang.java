package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Entity
@Table(name = "chi_tiet_don_hang")
@Data
public class ChiTietDonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maCtdh")
    private Integer maCtdh;
    @Column(name = "so_luong")
    private Integer soLuong;
    @Column(name = "don_gia")
    private java.math.BigDecimal donGia;
    @Column(name = "thanh_tien")
    private java.math.BigDecimal thanhTien;
    @ManyToOne @JoinColumn(name="ma_don")
    private DonHang donHang;
    @ManyToOne @JoinColumn(name="ma_mon")
    private MonAn monAn;
}
