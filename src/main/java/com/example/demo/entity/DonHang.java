package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@Entity
@Table(name = "don_hang")
@Data
public class DonHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ma_don")
    private Integer maDon;
    @Column(name = "ngay_dat")
    private java.time.LocalDateTime ngayDat;
    @Column(name = "tong_tien")
    private java.math.BigDecimal tongTien;
    @Column(name = "trang_thai")
    private String trangThai;
    @ManyToOne @JoinColumn(name="ma_ban")
    private BanAn ban;
    @ManyToOne @JoinColumn(name="ma_nv")
    private NhanVien nhanVien;
}
