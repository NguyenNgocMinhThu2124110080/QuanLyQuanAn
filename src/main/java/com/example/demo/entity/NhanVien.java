package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Entity
@Table(name = "nhan_vien")
@Data
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nv_id")
    private Integer nvId;
    @Column(name = "chuc_vu")
    private String chucVu;
    private java.math.BigDecimal luong;
    @Column(name = "ngay_vao_lam")
    private java.time.LocalDate ngayVaoLam;
    @Column(name = "trang_thai")
    private Integer trangThai;
    @OneToOne @JoinColumn(name="user_id")
    private NguoiDung nguoiDung;
}
