package com.example.demo.controller;

import com.example.demo.entity.ChiTietDonHang;
import com.example.demo.repository.ChiTietDonHangRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Buổi 3: Quản lý Chi tiết Đơn hàng
@RestController
@RequestMapping("/api/chi-tiet-don-hang")
@RequiredArgsConstructor
public class ChiTietDonHangController {

    private final ChiTietDonHangRepository repository;

    @GetMapping("/don-hang/{maDon}")
    public List<ChiTietDonHang> layTheoDonHang(@PathVariable Integer maDon) {
        return repository.findByDonHang_MaDon(maDon);
    }

    @GetMapping
    public List<ChiTietDonHang> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChiTietDonHang> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ChiTietDonHang create(@RequestBody ChiTietDonHang entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChiTietDonHang> update(@PathVariable Integer id, @RequestBody ChiTietDonHang entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setMaCtdh(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
