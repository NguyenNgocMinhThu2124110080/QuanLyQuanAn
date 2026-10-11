package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "kho")
@Data
public class Kho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "khoId")
    private Integer khoId;
    @Column(name = "ten_kho")
    private String tenKho;
    @Column(name = "dia_chi")
    private String diaChi;
    @Column(name = "trang_thai")
    private Integer trangThai;
}
