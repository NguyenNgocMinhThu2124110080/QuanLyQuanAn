package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Entity
@Table(name = "thanh_toan")
@Data
public class ThanhToan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maTt")
    private Integer maTt;
    @Column(name = "phuong_thuc")
    private String phuongThuc;
    @Column(name = "so_tien")
    private java.math.BigDecimal soTien;
    @Column(name = "thoi_gian")
    private java.time.LocalDateTime thoiGian;
    @Column(name = "trang_thai")
    private String trangThai;
    @OneToOne @JoinColumn(name="ma_don")
    private DonHang donHang;
    @ManyToOne @JoinColumn(name="ma_nv")
    private NhanVien nhanVien;
}
