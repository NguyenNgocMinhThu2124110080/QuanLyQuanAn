package com.example.demo.controller;

import com.example.demo.dto.NguyenLieuTonKhoResponse;
import com.example.demo.entity.NguyenLieu;
import com.example.demo.repository.NguyenLieuRepository;
import com.example.demo.service.KhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: API Quản lý Nguyên liệu & Báo cáo Tồn kho cảnh báo
@RestController
@RequestMapping("/api/nguyen-lieu")
@RequiredArgsConstructor
public class NguyenLieuController {

    private final NguyenLieuRepository nguyenLieuRepository;
    private final KhoService khoService;

    @GetMapping("/bao-cao-ton-kho")
    public List<NguyenLieuTonKhoResponse> layBaoCaoTonKho() {
        return khoService.baoCaoTonKho();
    }

    @GetMapping("/tim-kiem")
    public List<NguyenLieu> timKiemTheoTen(@RequestParam String ten) {
        return nguyenLieuRepository.findByTenNguyenLieuContainingIgnoreCase(ten);
    }

    @GetMapping("/nha-cung-cap/{nccId}")
    public List<NguyenLieu> layTheoNhaCungCap(@PathVariable Integer nccId) {
        return nguyenLieuRepository.findByNhaCungCap_NccId(nccId);
    }

    @GetMapping
    public List<NguyenLieu> getAll() {
        return nguyenLieuRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguyenLieu> getById(@PathVariable Integer id) {
        return nguyenLieuRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NguyenLieu create(@RequestBody NguyenLieu entity) {
        return nguyenLieuRepository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NguyenLieu> update(@PathVariable Integer id, @RequestBody NguyenLieu entity) {
        if (!nguyenLieuRepository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setNlId(id);
        return ResponseEntity.ok(nguyenLieuRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!nguyenLieuRepository.existsById(id)) return ResponseEntity.notFound().build();
        nguyenLieuRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
