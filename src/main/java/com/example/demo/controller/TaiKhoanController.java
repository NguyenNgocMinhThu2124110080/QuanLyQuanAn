package com.example.demo.controller;

import com.example.demo.dto.DoiMatKhauRequest;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import com.example.demo.service.TaiKhoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: API Quản lý Tài khoản, Đăng nhập & Đổi mật khẩu
@RestController
@RequestMapping("/api/tai-khoan")
@RequiredArgsConstructor
public class TaiKhoanController {

    private final TaiKhoanRepository taiKhoanRepository;
    private final TaiKhoanService taiKhoanService;

    @PostMapping("/dang-nhap")
    public ResponseEntity<LoginResponse> dangNhap(@RequestBody LoginRequest request) {
        LoginResponse response = taiKhoanService.dangNhap(request);
        if (!response.isSuccess()) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/doi-mat-khau")
    public ResponseEntity<String> doiMatKhau(@RequestBody DoiMatKhauRequest request) {
        boolean success = taiKhoanService.doiMatKhau(request);
        if (success) {
            return ResponseEntity.ok("Đổi mật khẩu thành công!");
        }
        return ResponseEntity.badRequest().body("Mật khẩu cũ không đúng hoặc tài khoản không tồn tại!");
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<String> doiTrangThai(@PathVariable Integer id, @RequestParam Integer trangThai) {
        boolean ok = taiKhoanService.doiTrangThaiTaiKhoan(id, trangThai);
        return ok ? ResponseEntity.ok("Cập nhật trạng thái thành công!") : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaiKhoan> getById(@PathVariable Integer id) {
        return taiKhoanRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public TaiKhoan create(@RequestBody TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaiKhoan> update(@PathVariable Integer id, @RequestBody TaiKhoan taiKhoan) {
        if (!taiKhoanRepository.existsById(id)) return ResponseEntity.notFound().build();
        taiKhoan.setTaiKhoanId(id);
        return ResponseEntity.ok(taiKhoanRepository.save(taiKhoan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!taiKhoanRepository.existsById(id)) return ResponseEntity.notFound().build();
        taiKhoanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
