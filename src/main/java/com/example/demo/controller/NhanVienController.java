package com.example.demo.controller;

import com.example.demo.dto.TaoNhanVienRequest;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import com.example.demo.service.NhanVienService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: API Quản lý Nhân sự & Tạo nhân viên trọn gói
@RestController
@RequestMapping("/api/nhan-vien")
@RequiredArgsConstructor
public class NhanVienController {

    private final NhanVienRepository nhanVienRepository;
    private final NhanVienService nhanVienService;

    @PostMapping("/tao-tron-goi")
    public ResponseEntity<?> taoTronGoi(@RequestBody TaoNhanVienRequest request) {
        try {
            NhanVien nv = nhanVienService.taoNhanVienTronGoi(request);
            return ResponseEntity.ok(nv);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/luong-chuc-vu")
    public ResponseEntity<?> capNhatLuongChucVu(
            @PathVariable Integer id,
            @RequestParam(required = false) BigDecimal luong,
            @RequestParam(required = false) String chucVu) {
        try {
            NhanVien nv = nhanVienService.capNhatLuongVaChucVu(id, luong, chucVu);
            return ResponseEntity.ok(nv);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<NhanVien> getAll() {
        return nhanVienService.layTatCa();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhanVien> getById(@PathVariable Integer id) {
        return nhanVienService.timTheoId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NhanVien create(@RequestBody NhanVien nhanVien) {
        return nhanVienRepository.save(nhanVien);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhanVien> update(@PathVariable Integer id, @RequestBody NhanVien nhanVien) {
        if (!nhanVienRepository.existsById(id)) return ResponseEntity.notFound().build();
        nhanVien.setNvId(id);
        return ResponseEntity.ok(nhanVienRepository.save(nhanVien));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!nhanVienRepository.existsById(id)) return ResponseEntity.notFound().build();
        nhanVienRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
