package com.example.demo.service;

import com.example.demo.dto.DoanhThuResponse;
import com.example.demo.dto.ThanhToanRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Nghiệp vụ Thanh toán Hoá đơn, Giải phóng Bàn & Thống kê Doanh thu
@Service
public class ThanhToanService {

    private final ThanhToanRepository thanhToanRepository;
    private final DonHangRepository donHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final BanAnRepository banAnRepository;
    private final ChiTietDonHangRepository chiTietDonHangRepository;

    public ThanhToanService(ThanhToanRepository thanhToanRepository,
                            DonHangRepository donHangRepository,
                            NhanVienRepository nhanVienRepository,
                            BanAnRepository banAnRepository,
                            ChiTietDonHangRepository chiTietDonHangRepository) {
        this.thanhToanRepository = thanhToanRepository;
        this.donHangRepository = donHangRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.banAnRepository = banAnRepository;
        this.chiTietDonHangRepository = chiTietDonHangRepository;
    }

    /**
     * Thực hiện thanh toán đơn hàng:
     * 1. Xác thực đơn hàng và nhân viên thu ngân
     * 2. RÀNG BUỘC NGHIỆP VỤ QUAN TRỌNG:
     *    - Không thanh toán đơn đã thanh toán trước đó
     *    - Không thanh toán đơn đã bị hủy
     *    - Không thanh toán đơn trống chưa gọi món
     *    - Bắt buộc món ăn phải được bếp hoàn thành chế biến (HOAN_THANH) trước khi thanh toán
     * 3. Tạo bản ghi giao dịch thanh toán
     * 4. Chuyển trạng thái đơn hàng sang DA_THANH_TOAN
     * 5. Giải phóng bàn ăn về trạng thái TRONG
     */
    @Transactional
    public ThanhToan thucHienThanhToan(ThanhToanRequest req) {
        // 1. Tìm đơn hàng
        DonHang donHang = donHangRepository.findById(req.getMaDon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + req.getMaDon()));

        // RÀNG BUỘC 1: Đơn hàng đã thanh toán trước đó
        if ("DA_THANH_TOAN".equalsIgnoreCase(donHang.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này đã được thanh toán trước đó rồi!");
        }

        // RÀNG BUỘC 2: Đơn hàng đã bị hủy
        if ("HUY".equalsIgnoreCase(donHang.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này đã bị hủy! Không thể thực hiện thanh toán.");
        }

        // RÀNG BUỘC 3: Đơn hàng chưa có món ăn nào (khách chưa gọi món gì)
        List<ChiTietDonHang> dsMon = chiTietDonHangRepository.findByDonHang_MaDon(donHang.getMaDon());
        if (dsMon.isEmpty() || donHang.getTongTien() == null || donHang.getTongTien().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Đơn hàng chưa có món ăn nào! Khách chưa gọi món, không thể thanh toán.");
        }

        // RÀNG BUỘC 4 (QUAN TRỌNG): Chỉ được thanh toán khi bếp đã hoàn thành chế biến toàn bộ món ăn (HOAN_THANH)
        if (!"HOAN_THANH".equalsIgnoreCase(donHang.getTrangThai())) {
            if ("DANG_CHE_BIEN".equalsIgnoreCase(donHang.getTrangThai())) {
                throw new IllegalStateException("Không thể thanh toán! Món ăn đang được bếp chế biến (chưa nấu xong). Vui lòng đợi bếp bấm 'Hoàn thành món' trên màn hình Bếp KDS.");
            } else if ("MOI".equalsIgnoreCase(donHang.getTrangThai())) {
                throw new IllegalStateException("Không thể thanh toán! Đơn hàng mới tạo, chưa gọi món hoặc chưa gửi bếp chế biến hoàn tất.");
            } else {
                throw new IllegalStateException("Không thể thanh toán! Đơn hàng đang ở trạng thái '" + donHang.getTrangThai() + "', chưa hoàn thành chế biến.");
            }
        }

        // 2. Tìm nhân viên thu ngân
        NhanVien nv = nhanVienRepository.findById(req.getMaNv())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên thu ngân ID: " + req.getMaNv()));

        // 3. Tạo thông tin thanh toán
        ThanhToan tt = new ThanhToan();
        tt.setDonHang(donHang);
        tt.setNhanVien(nv);
        tt.setPhuongThuc(req.getPhuongThuc() != null ? req.getPhuongThuc() : "TIEN_MAT");
        tt.setSoTien(donHang.getTongTien() != null ? donHang.getTongTien() : BigDecimal.ZERO);
        tt.setThoiGian(LocalDateTime.now());
        tt.setTrangThai("THANH_CONG");

        ThanhToan ttSaved = thanhToanRepository.save(tt);

        // 4. Cập nhật trạng thái đơn hàng
        donHang.setTrangThai("DA_THANH_TOAN");
        donHangRepository.save(donHang);

        // 5. Cập nhật trạng thái bàn ăn về TRONG (trống bàn cho khách sau)
        if (donHang.getBan() != null) {
            BanAn ban = donHang.getBan();
            ban.setTrangThai("TRONG");
            banAnRepository.save(ban);
        }

        return ttSaved;
    }

    /**
     * Thống kê tổng doanh thu theo ngày cụ thể và phân loại theo phương thức thanh toán
     */
    public DoanhThuResponse thongKeDoanhThuTheoNgay(LocalDate ngay) {
        if (ngay == null) ngay = LocalDate.now();

        LocalDateTime batDau = ngay.atStartOfDay();
        LocalDateTime ketThuc = ngay.atTime(LocalTime.MAX);

        List<ThanhToan> danhSach = thanhToanRepository.findByThoiGianBetween(batDau, ketThuc);

        BigDecimal tongDoanhThu = BigDecimal.ZERO;
        BigDecimal tienMat = BigDecimal.ZERO;
        BigDecimal chuyenKhoan = BigDecimal.ZERO;
        BigDecimal the = BigDecimal.ZERO;

        for (ThanhToan tt : danhSach) {
            if ("THANH_CONG".equalsIgnoreCase(tt.getTrangThai())) {
                BigDecimal soTien = tt.getSoTien() != null ? tt.getSoTien() : BigDecimal.ZERO;
                tongDoanhThu = tongDoanhThu.add(soTien);

                if ("TIEN_MAT".equalsIgnoreCase(tt.getPhuongThuc())) {
                    tienMat = tienMat.add(soTien);
                } else if ("CHUYEN_KHOAN".equalsIgnoreCase(tt.getPhuongThuc())) {
                    chuyenKhoan = chuyenKhoan.add(soTien);
                } else if ("THE".equalsIgnoreCase(tt.getPhuongThuc())) {
                    the = the.add(soTien);
                }
            }
        }

        return DoanhThuResponse.builder()
                .ngay(ngay)
                .tongSoDonThanhToan((long) danhSach.size())
                .tongDoanhThu(tongDoanhThu)
                .tienMat(tienMat)
                .chuyenKhoan(chuyenKhoan)
                .the(the)
                .build();
    }
}
