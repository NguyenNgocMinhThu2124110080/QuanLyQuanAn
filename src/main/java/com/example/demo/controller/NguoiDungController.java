package com.example.demo.controller;

import com.example.demo.entity.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: Quản lý Thông tin Người dùng
@RestController
@RequestMapping("/api/nguoi-dung")
@RequiredArgsConstructor
public class NguoiDungController {

    private final NguoiDungRepository repository;

    @GetMapping
    public List<NguoiDung> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NguoiDung> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/so-dien-thoai/{sdt}")
    public ResponseEntity<NguoiDung> getBySdt(@PathVariable String sdt) {
        return repository.findBySoDienThoai(sdt)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NguoiDung create(@RequestBody NguoiDung entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NguoiDung> update(@PathVariable Integer id, @RequestBody NguoiDung entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setUserId(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
