package com.example.demo.service;

import com.example.demo.dto.NguyenLieuTonKhoResponse;
import com.example.demo.dto.NhapXuatKhoRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: Nghiệp vụ Quản lý Kho, Nhập/Xuất Nguyên liệu & Cảnh báo Tồn kho
@Service
@RequiredArgsConstructor
public class KhoService {

    private final KhoRepository khoRepository;
    private final NguyenLieuRepository nguyenLieuRepository;
    private final PhieuNhapXuatRepository phieuNhapXuatRepository;
    private final NhaCungCapRepository nhaCungCapRepository;
    private final NhanVienRepository nhanVienRepository;

    /**
     * Xử lý tạo phiếu Nhập kho hoặc Xuất kho nguyên liệu:
     * - Tự động cộng/trừ số lượng tồn kho
     * - Chặn xuất nếu số lượng tồn không đủ
     */
    @Transactional
    public PhieuNhapXuat xuLyNhapXuat(NhapXuatKhoRequest req) {
        // 1. Kiểm tra nguyên liệu
        NguyenLieu nl = nguyenLieuRepository.findById(req.getNlId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nguyên liệu ID: " + req.getNlId()));

        // 2. Kiểm tra kho và nhân viên
        Kho kho = khoRepository.findById(req.getKhoId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy kho lưu trữ ID: " + req.getKhoId()));

        NhanVien nv = nhanVienRepository.findById(req.getNvId())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên thủ kho ID: " + req.getNvId()));

        NhaCungCap ncc = null;
        if (req.getNccId() != null) {
            ncc = nhaCungCapRepository.findById(req.getNccId()).orElse(null);
        }

        BigDecimal soLuong = req.getSoLuong();
        BigDecimal donGia = req.getDonGia() != null ? req.getDonGia() : (nl.getGiaNhap() != null ? nl.getGiaNhap() : BigDecimal.ZERO);
        BigDecimal tongTien = donGia.multiply(soLuong);

        // 3. Xử lý logic tồn kho theo loại phiếu
        if ("NHAP".equalsIgnoreCase(req.getLoaiPhieu())) {
            // Cộng dồn tồn kho
            BigDecimal tonMoi = (nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO).add(soLuong);
            nl.setSoLuongTon(tonMoi);
            if (req.getDonGia() != null) {
                nl.setGiaNhap(req.getDonGia()); // Cập nhật giá nhập mới nhất
            }
            if (ncc != null) {
                nl.setNhaCungCap(ncc);
            }
        } else if ("XUAT".equalsIgnoreCase(req.getLoaiPhieu())) {
            // Kiểm tra số lượng tồn
            BigDecimal tonHienTai = nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO;
            if (tonHienTai.compareTo(soLuong) < 0) {
                throw new IllegalStateException("Kho không đủ số lượng để xuất! Hiện tại chỉ còn: " + tonHienTai + " " + nl.getDonViTinh());
            }
            // Trừ tồn kho
            nl.setSoLuongTon(tonHienTai.subtract(soLuong));
        } else {
            throw new IllegalArgumentException("Loại phiếu không hợp lệ (chỉ chấp nhận 'NHAP' hoặc 'XUAT')!");
        }

        nguyenLieuRepository.save(nl);

        // 4. Tạo bản ghi phiếu nhập xuất
        PhieuNhapXuat pnx = new PhieuNhapXuat();
        pnx.setLoaiPhieu(req.getLoaiPhieu().toUpperCase());
        pnx.setNgayNhapXuat(LocalDateTime.now());
        pnx.setSoLuong(soLuong);
        pnx.setDonGia(donGia);
        pnx.setTongTien(tongTien);
        pnx.setGhiChu(req.getGhiChu());
        pnx.setNguyenLieu(nl);
        pnx.setNhaCungCap(ncc);
        pnx.setNhanVien(nv);
        pnx.setKho(kho);

        return phieuNhapXuatRepository.save(pnx);
    }

    /**
     * Báo cáo tồn kho toàn bộ nguyên liệu kèm mức độ cảnh báo (AN TOÀN / SẮP HẾT / HẾT HÀNG)
     */
    public List<NguyenLieuTonKhoResponse> baoCaoTonKho() {
        List<NguyenLieu> list = nguyenLieuRepository.findAll();
        List<NguyenLieuTonKhoResponse> result = new ArrayList<>();

        for (NguyenLieu nl : list) {
            BigDecimal ton = nl.getSoLuongTon() != null ? nl.getSoLuongTon() : BigDecimal.ZERO;
            String mucDo = "AN_TOAN";
            if (ton.compareTo(BigDecimal.ZERO) <= 0) {
                mucDo = "HET_HANG";
            } else if (ton.compareTo(new BigDecimal("5.0")) <= 0) {
                mucDo = "SAP_HET";
            }

            result.add(NguyenLieuTonKhoResponse.builder()
                    .nlId(nl.getNlId())
                    .tenNguyenLieu(nl.getTenNguyenLieu())
                    .donViTinh(nl.getDonViTinh())
                    .soLuongTon(ton)
                    .giaNhap(nl.getGiaNhap())
                    .tenNhaCungCap(nl.getNhaCungCap() != null ? nl.getNhaCungCap().getTenNcc() : "Chưa có")
                    .mucDoCanhBao(mucDo)
                    .build());
        }
        return result;
    }

    /**
     * Tra cứu lịch sử nhập xuất của một nguyên liệu
     */
    public List<PhieuNhapXuat> lichSuTheoNguyenLieu(Integer nlId) {
        return phieuNhapXuatRepository.findByNguyenLieu_NlId(nlId);
    }
}
