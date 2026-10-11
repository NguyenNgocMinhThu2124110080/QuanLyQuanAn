package com.example.demo.service;

import com.example.demo.dto.TaoNhanVienRequest;
import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.PhanQuyen;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.PhanQuyenRepository;
import com.example.demo.repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: Nghiệp vụ Quản lý Hồ sơ Nhân sự & Phân quyền
@Service
@RequiredArgsConstructor
public class NhanVienService {

    private final NhanVienRepository nhanVienRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final TaiKhoanRepository taiKhoanRepository;
    private final PhanQuyenRepository phanQuyenRepository;

    /**
     * Tạo mới một nhân viên trọn gói (gồm Tài khoản -> Người dùng -> Hồ sơ nhân viên)
     */
    @Transactional
    public NhanVien taoNhanVienTronGoi(TaoNhanVienRequest req) {
        // 1. Kiểm tra trùng username hoặc email
        if (taiKhoanRepository.existsByUsername(req.getUsername())) {
            throw new IllegalArgumentException("Username '" + req.getUsername() + "' đã tồn tại!");
        }
        if (taiKhoanRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email '" + req.getEmail() + "' đã được sử dụng!");
        }

        // 2. Lấy phân quyền
        PhanQuyen phanQuyen = phanQuyenRepository.findById(req.getPqId())
                .orElseThrow(() -> new IllegalArgumentException("Mã phân quyền không tồn tại: " + req.getPqId()));

        // 3. Tạo tài khoản
        TaiKhoan tk = new TaiKhoan();
        tk.setUsername(req.getUsername());
        tk.setPassword(req.getPassword());
        tk.setEmail(req.getEmail());
        tk.setPhanQuyen(phanQuyen);
        tk.setTrangThai(1);
        TaiKhoan tkSaved = taiKhoanRepository.save(tk);

        // 4. Tạo người dùng
        NguoiDung nd = new NguoiDung();
        nd.setHoTen(req.getHoTen());
        nd.setSoDienThoai(req.getSoDienThoai());
        nd.setDiaChi(req.getDiaChi());
        nd.setGioiTinh(req.getGioiTinh());
        nd.setNgaySinh(req.getNgaySinh());
        nd.setTrangThai(1);
        nd.setTaiKhoan(tkSaved);
        NguoiDung ndSaved = nguoiDungRepository.save(nd);

        // 5. Tạo hồ sơ nhân viên
        NhanVien nv = new NhanVien();
        nv.setChucVu(req.getChucVu());
        nv.setLuong(req.getLuong());
        nv.setNgayVaoLam(LocalDate.now());
        nv.setTrangThai(1);
        nv.setNguoiDung(ndSaved);

        return nhanVienRepository.save(nv);
    }

    /**
     * Cập nhật mức lương và chức vụ nhân viên
     */
    public NhanVien capNhatLuongVaChucVu(Integer nvId, BigDecimal luongMoi, String chucVuMoi) {
        NhanVien nv = nhanVienRepository.findById(nvId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy nhân viên ID: " + nvId));
        if (luongMoi != null) nv.setLuong(luongMoi);
        if (chucVuMoi != null && !chucVuMoi.isBlank()) nv.setChucVu(chucVuMoi);
        return nhanVienRepository.save(nv);
    }

    /**
     * Lấy toàn bộ danh sách nhân viên
     */
    public List<NhanVien> layTatCa() {
        return nhanVienRepository.findAll();
    }

    /**
     * Tìm nhân viên theo ID
     */
    public Optional<NhanVien> timTheoId(Integer id) {
        return nhanVienRepository.findById(id);
    }
}
