package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.Data;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@Entity
@Table(name = "cong_thuc_mon")
@Data
public class CongThucMon {
    @EmbeddedId
    private CongThucMonId id;
    @ManyToOne @JoinColumn(name="ma_mon",insertable=false,updatable=false)
    private MonAn monAn;
    @ManyToOne @JoinColumn(name="nl_id",insertable=false,updatable=false)
    private NguyenLieu nguyenLieu;
    @Column(name = "so_luong_dung")
    private java.math.BigDecimal soLuongDung;
}
