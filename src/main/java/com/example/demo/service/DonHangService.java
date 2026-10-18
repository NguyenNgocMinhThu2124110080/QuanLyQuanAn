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
// Buổi 4: Nghiệp vụ Quy trình Đơn hàng, Vòng đời trạng thái, Chặn sửa/xóa đơn đã thanh toán & Tự trừ kho
@Service
@RequiredArgsConstructor
public class DonHangService {

    private final DonHangRepository donHangRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;
    private final BanAnRepository banAnRepository;
    private final MonAnRepository monAnRepository;
    private final NhanVienRepository nhanVienRepository;
    private final KhoService khoService;

    /**
     * Mở bàn và tạo đơn hàng mới
     * - Cập nhật trạng thái bàn sang CO_KHACH
     * - Trạng thái đơn ban đầu: MOI
     */
    @Transactional
    public DonHang taoDonHang(TaoDonHangRequest req) {
        if (req.getMaBan() == null) throw new IllegalArgumentException("Mã bàn không được để trống!");
        if (req.getMaNv() == null) throw new IllegalArgumentException("Mã nhân viên phục vụ không được để trống!");

        BanAn ban = banAnRepository.findById(req.getMaBan())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy bàn ăn với mã: " + req.getMaBan()));

        NhanVien nv = nhanVienRepository.findById(req.getMaNv())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên với mã: " + req.getMaNv()));

        // Đổi trạng thái bàn sang CO_KHACH
        ban.setTrangThai("CO_KHACH");
        banAnRepository.save(ban);

        DonHang dh = new DonHang();
        dh.setBan(ban);
        dh.setNhanVien(nv);
        dh.setNgayDat(LocalDateTime.now());
        dh.setTrangThai("MOI");
        dh.setTongTien(BigDecimal.ZERO);

        return donHangRepository.save(dh);
    }

    /**
     * Thêm món vào đơn hàng (Gọi món)
     * - RÀNG BUỘC: Không thể thêm món cho đơn đã thanh toán hoặc đã hủy
     * - TÍNH NĂNG LIÊN THÔNG (BUỔI 4): Tự động trừ kho nguyên liệu theo công thức món ăn
     * - Tự động tính Thành tiền = Đơn giá * Số lượng và cộng dồn Tổng tiền đơn hàng
     */
    @Transactional
    public ChiTietDonHang themMonVaoDon(ThemMonVaoDonRequest req) {
        if (req.getMaDon() == null) throw new IllegalArgumentException("Mã đơn hàng không được để trống!");
        if (req.getMaMon() == null) throw new IllegalArgumentException("Mã món ăn không được để trống!");
        if (req.getSoLuong() == null || req.getSoLuong() <= 0) {
            throw new IllegalArgumentException("Số lượng món gọi phải lớn hơn 0!");
        }

        DonHang dh = donHangRepository.findById(req.getMaDon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + req.getMaDon()));

        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này ĐÃ THANH TOÁN! Không được phép thêm món.");
        }
        if ("HUY".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này ĐÃ BỊ HỦY! Không thể thêm món.");
        }

        MonAn mon = monAnRepository.findById(req.getMaMon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy món ăn ID: " + req.getMaMon()));

        if (mon.getTrangThai() != null && mon.getTrangThai() == 0) {
            throw new IllegalStateException("Món '" + mon.getTenMon() + "' hiện đang tạm ngưng phục vụ!");
        }

        // Tự động trừ kho nguyên liệu tương ứng với công thức món ăn
        Integer nvId = dh.getNhanVien() != null ? dh.getNhanVien().getNvId() : null;
        khoService.truKhoKhiBanMon(mon.getMaMon(), req.getSoLuong(), nvId, null);

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

        // Chuyển trạng thái đơn sang DANG_CHE_BIEN nếu đang là MOI
        if ("MOI".equalsIgnoreCase(dh.getTrangThai())) {
            dh.setTrangThai("DANG_CHE_BIEN");
        }

        donHangRepository.save(dh);

        return savedCtdh;
    }

    /**
     * Cập nhật trạng thái vòng đời của đơn hàng:
     * MOI -> DANG_CHE_BIEN -> HOAN_THANH -> DA_THANH_TOAN / HUY
     */
    @Transactional
    public DonHang capNhatTrangThaiDon(Integer maDon, String trangThaiMoi) {
        DonHang dh = donHangRepository.findById(maDon)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + maDon));

        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng ĐÃ THANH TOÁN! Không thể thay đổi trạng thái.");
        }
        if ("HUY".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng ĐÃ BỊ HỦY! Không thể thay đổi trạng thái.");
        }

        dh.setTrangThai(trangThaiMoi.toUpperCase());
        return donHangRepository.save(dh);
    }

    /**
     * RÀNG BUỘC QUAN TRỌNG: Xóa đơn hàng
     * Tuyệt đối KHÔNG được xóa đơn hàng đã thanh toán
     */
    @Transactional
    public void xoaDonHang(Integer maDon) {
        DonHang dh = donHangRepository.findById(maDon)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + maDon));

        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("KHÔNG THỂ XÓA đơn hàng đã thanh toán thành công!");
        }

        // Giải phóng bàn nếu đơn đang mở
        if (dh.getBan() != null && !"DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            BanAn ban = dh.getBan();
            ban.setTrangThai("TRONG");
            banAnRepository.save(ban);
        }

        donHangRepository.delete(dh);
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
        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng ĐÃ THANH TOÁN! Không thể hủy hoặc xóa món.");
        }
        if ("HUY".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng ĐÃ BỊ HỦY!");
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

        if ("DA_THANH_TOAN".equalsIgnoreCase(dh.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng ĐÃ THANH TOÁN! Không được phép hủy.");
        }

        dh.setTrangThai("HUY");
        donHangRepository.save(dh);

        if (dh.getBan() != null) {
            BanAn ban = dh.getBan();
            ban.setTrangThai("TRONG");
            banAnRepository.save(ban);
        }
    }
}
