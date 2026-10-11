package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@Entity
@Table(name = "nguoi_dung")
@Data
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "ho_ten")
    private String hoTen;
    @Column(name = "ngay_sinh")
    private java.time.LocalDate ngaySinh;
    @Column(name = "gioi_tinh")
    private String gioiTinh;
    @Column(name = "so_dien_thoai")
    private String soDienThoai;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "trang_thai")
    private Integer trangThai;
    @OneToOne @JoinColumn(name="tai_khoan_id")
    private TaiKhoan taiKhoan;
}
