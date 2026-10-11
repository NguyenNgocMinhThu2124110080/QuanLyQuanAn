package com.example.demo.service;

import com.example.demo.dto.DonHangChiTietResponse;
import com.example.demo.dto.TaoDonHangRequest;
import com.example.demo.dto.ThemMonVaoDonRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Buổi 3: Nghiệp vụ Mở bàn, Đặt món, Tự động tính thành tiền & Tổng tiền đơn hàng
@Service
@RequiredArgsConstructor
public class DonHangService {

    private final DonHangRepository donHangRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;
    private final BanAnRepository banAnRepository;
    private final MonAnRepository monAnRepository;
    private final NhanVienRepository nhanVienRepository;

    /**
     * Mở bàn và tạo đơn hàng mới
     */
    @Transactional
    public DonHang taoDonHang(TaoDonHangRequest req) {
        BanAn ban = banAnRepository.findById(req.getMaBan())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bàn ăn ID: " + req.getMaBan()));

        NhanVien nv = nhanVienRepository.findById(req.getMaNv())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên ID: " + req.getMaNv()));

        // Đổi trạng thái bàn sang CO_KHACH
        ban.setTrangThai("CO_KHACH");
        banAnRepository.save(ban);

        DonHang dh = new DonHang();
        dh.setBan(ban);
        dh.setNhanVien(nv);
        dh.setNgayDat(LocalDateTime.now());
        dh.setTrangThai("DANG_PHUC_VU");
        dh.setTongTien(BigDecimal.ZERO);

        return donHangRepository.save(dh);
    }

    /**
     * Thêm món vào đơn hàng (Gọi món)
     * - Tự động tính Thành tiền = Đơn giá * Số lượng
     * - Tự động cộng dồn Tổng tiền của Đơn hàng
     */
    @Transactional
    public ChiTietDonHang themMonVaoDon(ThemMonVaoDonRequest req) {
        DonHang dh = donHangRepository.findById(req.getMaDon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + req.getMaDon()));

        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai()) || "HUY".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này không thể thêm món vì đã thanh toán hoặc đã huỷ!");
        }

        MonAn mon = monAnRepository.findById(req.getMaMon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy món ăn ID: " + req.getMaMon()));

        if (mon.getTrangThai() != null && mon.getTrangThai() == 0) {
            throw new IllegalStateException("Món '" + mon.getTenMon() + "' hiện đang tạm ngưng phục vụ!");
        }

        BigDecimal donGia = mon.getGia() != null ? mon.getGia() : BigDecimal.ZERO;
        BigDecimal thanhTien = donGia.multiply(BigDecimal.valueOf(req.getSoLuong()));

        // 1. Tạo chi tiết đơn hàng
        ChiTietDonHang ctdh = new ChiTietDonHang();
        ctdh.setDonHang(dh);
        ctdh.setMonAn(mon);
        ctdh.setSoLuong(req.getSoLuong());
        ctdh.setDonGia(donGia);
        ctdh.setThanhTien(thanhTien);

        ChiTietDonHang savedCtdh = chiTietDonHangRepository.save(ctdh);

        // 2. Cập nhật lại tổng tiền của đơn hàng
        BigDecimal tongTienMoi = (dh.getTongTien() != null ? dh.getTongTien() : BigDecimal.ZERO).add(thanhTien);
        dh.setTongTien(tongTienMoi);
        donHangRepository.save(dh);

        return savedCtdh;
    }

    /**
     * Xóa một món đã gọi khỏi đơn hàng (Huỷ món)
     * - Tự động trừ tiền khỏi Tổng tiền đơn hàng
     */
    @Transactional
    public void xoaMonKhoiDon(Integer maCtdh) {
        ChiTietDonHang ctdh = chiTietDonHangRepository.findById(maCtdh)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy chi tiết đơn hàng ID: " + maCtdh));

        DonHang dh = ctdh.getDonHang();
        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai()) || "HUY".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Không thể huỷ món của đơn đã thanh toán hoặc đã đóng!");
        }

        // Trừ tiền đơn hàng
        BigDecimal tongTienMoi = dh.getTongTien().subtract(ctdh.getThanhTien());
        if (tongTienMoi.compareTo(BigDecimal.ZERO) < 0) tongTienMoi = BigDecimal.ZERO;
        dh.setTongTien(tongTienMoi);
        donHangRepository.save(dh);

        chiTietDonHangRepository.delete(ctdh);
    }

    /**
     * Lấy toàn bộ thông tin chi tiết của hoá đơn (Kèm danh sách các món đã gọi)
     */
    public DonHangChiTietResponse layChiTietHoaDon(Integer maDon) {
        DonHang dh = donHangRepository.findById(maDon)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + maDon));

        List<ChiTietDonHang> ctdhList = chiTietDonHangRepository.findByDonHang_MaDon(maDon);
        List<DonHangChiTietResponse.MonItemResponse> items = new ArrayList<>();

        for (ChiTietDonHang ct : ctdhList) {
            items.add(DonHangChiTietResponse.MonItemResponse.builder()
                    .maCtdh(ct.getMaCtdh())
                    .maMon(ct.getMonAn() != null ? ct.getMonAn().getMaMon() : null)
                    .tenMon(ct.getMonAn() != null ? ct.getMonAn().getTenMon() : "Món không xác định")
                    .soLuong(ct.getSoLuong())
                    .donGia(ct.getDonGia())
                    .thanhTien(ct.getThanhTien())
                    .build());
        }

        return DonHangChiTietResponse.builder()
                .maDon(dh.getMaDon())
                .tenBan(dh.getBan() != null ? dh.getBan().getTenBan() : "Chưa chọn bàn")
                .tenNhanVien(dh.getNhanVien() != null && dh.getNhanVien().getNguoiDung() != null 
                        ? dh.getNhanVien().getNguoiDung().getHoTen() : "Nhân viên")
                .ngayDat(dh.getNgayDat())
                .trangThai(dh.getTrangThai())
                .tongTien(dh.getTongTien())
                .danhSachMon(items)
                .build();
    }

    /**
     * Huỷ toàn bộ đơn hàng và trả lại bàn trống
     */
    @Transactional
    public void huyDonHang(Integer maDon) {
        DonHang dh = donHangRepository.findById(maDon)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + maDon));

        dh.setTrangThai("HUY");
        donHangRepository.save(dh);

        if (dh.getBan() != null) {
            BanAn ban = dh.getBan();
            ban.setTrangThai("TRONG");
            banAnRepository.save(ban);
        }
    }
}
