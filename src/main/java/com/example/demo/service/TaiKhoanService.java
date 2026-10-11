package com.example.demo.service;

import com.example.demo.dto.DoiMatKhauRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.NguoiDung;
import com.example.demo.entity.NhanVien;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.NguoiDungRepository;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: Nghiệp vụ Quản lý Tài khoản & Xác thực Đăng nhập
@Service
@RequiredArgsConstructor
public class TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NhanVienRepository nhanVienRepository;

    /**
     * Xác thực thông tin đăng nhập
     */
    public LoginResponse dangNhap(LoginRequest request) {
        Optional<TaiKhoan> optTk = taiKhoanRepository.findByUsername(request.getUsername());

        if (optTk.isEmpty()) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Tài khoản không tồn tại!")
                    .build();
        }

        TaiKhoan tk = optTk.get();

        // Kiểm tra trạng thái tài khoản
        if (tk.getTrangThai() != null && tk.getTrangThai() == 0) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Tài khoản đã bị khoá, vui lòng liên hệ Admin!")
                    .build();
        }

        // Kiểm tra mật khẩu
        if (!tk.getPassword().equals(request.getPassword())) {
            return LoginResponse.builder()
                    .success(false)
                    .message("Mật khẩu không chính xác!")
                    .build();
        }

        // Tìm thông tin người dùng và nhân viên liên kết
        Optional<NguoiDung> optNd = nguoiDungRepository.findByTaiKhoan(tk);
        String hoTen = optNd.map(NguoiDung::getHoTen).orElse(tk.getUsername());
        Integer userId = optNd.map(NguoiDung::getUserId).orElse(null);

        Integer nvId = null;
        if (optNd.isPresent()) {
            Optional<NhanVien> optNv = nhanVienRepository.findByNguoiDung(optNd.get());
            nvId = optNv.map(NhanVien::getNvId).orElse(null);
        }

        String vaiTro = tk.getPhanQuyen() != null ? tk.getPhanQuyen().getTenPq() : "CHUA_PHAN_QUYEN";

        return LoginResponse.builder()
                .success(true)
                .message("Đăng nhập thành công!")
                .username(tk.getUsername())
                .vaiTro(vaiTro)
                .hoTen(hoTen)
                .userId(userId)
                .nvId(nvId)
                .build();
    }

    /**
     * Đổi mật khẩu tài khoản
     */
    public boolean doiMatKhau(DoiMatKhauRequest request) {
        Optional<TaiKhoan> optTk = taiKhoanRepository.findByUsername(request.getUsername());
        if (optTk.isEmpty()) return false;

        TaiKhoan tk = optTk.get();
        if (!tk.getPassword().equals(request.getMatKhauCu())) {
            return false;
        }

        tk.setPassword(request.getMatKhauMoi());
        taiKhoanRepository.save(tk);
        return true;
    }

    /**
     * Khóa hoặc mở khóa tài khoản
     */
    public boolean doiTrangThaiTaiKhoan(Integer taiKhoanId, Integer trangThai) {
        return taiKhoanRepository.findById(taiKhoanId).map(tk -> {
            tk.setTrangThai(trangThai);
            taiKhoanRepository.save(tk);
            return true;
        }).orElse(false);
    }
}
