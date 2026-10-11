package com.example.demo.service;

import com.example.demo.dto.ThanhToanRequest;
import com.example.demo.entity.BanAn;
import com.example.demo.entity.DonHang;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.ThanhToan;
import com.example.demo.repository.BanAnRepository;
import com.example.demo.repository.DonHangRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.ThanhToanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: Nghiệp vụ Quy trình Thanh toán Hoá đơn & Giải phóng Bàn ăn
@Service
@RequiredArgsConstructor
public class ThanhToanService {

    private final ThanhToanRepository thanhToanRepository;
    private final DonHangRepository donHangRepository;
    private final NhanVienRepository nhanVienRepository;
    private final BanAnRepository banAnRepository;

    /**
     * Thực hiện thanh toán đơn hàng:
     * 1. Xác thực đơn hàng và nhân viên thu ngân
     * 2. Tạo bản ghi giao dịch thanh toán
     * 3. Chuyển trạng thái đơn hàng sang DA_THANH_TOAN
     * 4. Giải phóng bàn ăn về trạng thái TRONG
     */
    @Transactional
    public ThanhToan thucHienThanhToan(ThanhToanRequest req) {
        // 1. Tìm đơn hàng
        DonHang donHang = donHangRepository.findById(req.getMaDon())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy đơn hàng ID: " + req.getMaDon()));

        if ("DA_THANH_TOAN".equalsIgnoreCase(donHang.getTrangThai())) {
            throw new IllegalStateException("Đơn hàng này đã được thanh toán trước đó rồi!");
        }

        // 2. Tìm nhân viên thu ngân
        NhanVien nv = nhanVienRepository.findById(req.getMaNv())
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên thu ngân ID: " + req.getMaNv()));

        // 3. Tạo thông tin thanh toán
        ThanhToan tt = new ThanhToan();
        tt.setDonHang(donHang);
        tt.setNhanVien(nv);
        tt.setPhuongThuc(req.getPhuongThuc() != null ? req.getPhuongThuc() : "TIEN_MAT");
        tt.setSoTien(donHang.getTongTien());
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
}
