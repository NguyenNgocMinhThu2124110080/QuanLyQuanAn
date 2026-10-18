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
import com.example.demo.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Nghiệp vụ Quản lý Tài khoản, Xác thực Đăng nhập JWT & Mã hóa BCrypt (Đã chuẩn hóa)
@Service
public class TaiKhoanService {

    private final TaiKhoanRepository taiKhoanRepository;
    private final NguoiDungRepository nguoiDungRepository;
    private final NhanVienRepository nhanVienRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public TaiKhoanService(TaiKhoanRepository taiKhoanRepository,
                           NguoiDungRepository nguoiDungRepository,
                           NhanVienRepository nhanVienRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.taiKhoanRepository = taiKhoanRepository;
        this.nguoiDungRepository = nguoiDungRepository;
        this.nhanVienRepository = nhanVienRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Xác thực thông tin đăng nhập và sinh mã JWT Token
     */
    public LoginResponse dangNhap(LoginRequest request) {
        Optional<TaiKhoan> optTk = taiKhoanRepository.findByUsername(request.getUsername());

        if (optTk.isEmpty()) {
            return LoginResponse.failure("Tài khoản không tồn tại!");
        }

        TaiKhoan tk = optTk.get();

        // Kiểm tra trạng thái tài khoản
        if (tk.getTrangThai() != null && tk.getTrangThai() == 0) {
            return LoginResponse.failure("Tài khoản đã bị khoá, vui lòng liên hệ Admin!");
        }

        // Kiểm tra mật khẩu (hỗ trợ cả chuẩn mã hóa BCrypt và mật khẩu khởi tạo ban đầu)
        boolean isMatch = passwordEncoder.matches(request.getPassword(), tk.getPassword()) 
                       || request.getPassword().equals(tk.getPassword());

        if (!isMatch) {
            return LoginResponse.failure("Mật khẩu không chính xác!");
        }

        // Tự động nâng cấp mật khẩu sang mã hóa BCrypt nếu chưa mã hóa
        if (request.getPassword().equals(tk.getPassword()) && !tk.getPassword().startsWith("$2a$")) {
            tk.setPassword(passwordEncoder.encode(request.getPassword()));
            taiKhoanRepository.save(tk);
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

        String vaiTro = tk.getPhanQuyen() != null ? tk.getPhanQuyen().getTenPq() : "USER";

        // Sinh mã Token JWT chứa thông tin tài khoản và phân quyền
        String token = jwtTokenProvider.generateToken(tk.getUsername(), vaiTro);

        return LoginResponse.success(tk.getUsername(), vaiTro, hoTen, userId, nvId, token);
    }

    /**
     * Đổi mật khẩu tài khoản có mã hóa BCrypt
     */
    public boolean doiMatKhau(DoiMatKhauRequest request) {
        Optional<TaiKhoan> optTk = taiKhoanRepository.findByUsername(request.getUsername());
        if (optTk.isEmpty()) return false;

        TaiKhoan tk = optTk.get();
        boolean isMatch = passwordEncoder.matches(request.getMatKhauCu(), tk.getPassword()) 
                       || request.getMatKhauCu().equals(tk.getPassword());
        if (!isMatch) {
            return false;
        }

        // Mã hóa mật khẩu mới bằng BCrypt trước khi lưu
        tk.setPassword(passwordEncoder.encode(request.getMatKhauMoi()));
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
