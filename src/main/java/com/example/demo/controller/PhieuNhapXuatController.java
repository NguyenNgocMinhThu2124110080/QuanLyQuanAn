package com.example.demo.controller;

import com.example.demo.dto.NhapXuatKhoRequest;
import com.example.demo.entity.PhieuNhapXuat;
import com.example.demo.repository.PhieuNhapXuatRepository;
import com.example.demo.service.KhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: API Lập phiếu Nhập/Xuất kho & Tra cứu lịch sử
@RestController
@RequestMapping("/api/phieu-nhap-xuat")
@RequiredArgsConstructor
public class PhieuNhapXuatController {

    private final PhieuNhapXuatRepository phieuNhapXuatRepository;
    private final KhoService khoService;

    @PostMapping("/lap-phieu")
    public ResponseEntity<?> lapPhieuNhapXuat(@RequestBody NhapXuatKhoRequest request) {
        try {
            PhieuNhapXuat pnx = khoService.xuLyNhapXuat(request);
            return ResponseEntity.ok(pnx);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/lich-su/nguyen-lieu/{nlId}")
    public List<PhieuNhapXuat> layLichSuTheoNguyenLieu(@PathVariable Integer nlId) {
        return khoService.lichSuTheoNguyenLieu(nlId);
    }

    @GetMapping
    public List<PhieuNhapXuat> getAll() {
        return phieuNhapXuatRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhieuNhapXuat> getById(@PathVariable Integer id) {
        return phieuNhapXuatRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!phieuNhapXuatRepository.existsById(id)) return ResponseEntity.notFound().build();
        phieuNhapXuatRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
