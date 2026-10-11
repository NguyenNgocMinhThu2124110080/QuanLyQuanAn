package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "phieu_nhap_xuat")
@Data
public class PhieuNhapXuat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pn_id")
    private Integer pnId;
    @Column(name = "loai_phieu")
    private String loaiPhieu;
    @Column(name = "ngay_nhap_xuat")
    private java.time.LocalDateTime ngayNhapXuat;
    @Column(name = "so_luong")
    private java.math.BigDecimal soLuong;
    @Column(name = "don_gia")
    private java.math.BigDecimal donGia;
    @Column(name = "tong_tien")
    private java.math.BigDecimal tongTien;
    @Column(name = "ghi_chu")
    private String ghiChu;
    @ManyToOne @JoinColumn(name="nl_id")
    private NguyenLieu nguyenLieu;
    @ManyToOne @JoinColumn(name="ncc_id")
    private NhaCungCap nhaCungCap;
    @ManyToOne @JoinColumn(name="nv_id")
    private NhanVien nhanVien;
    @ManyToOne @JoinColumn(name="kho_id")
    private Kho kho;
}
