package com.example.demo.controller;

import com.example.demo.entity.PhanQuyen;
import com.example.demo.repository.PhanQuyenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: Quản lý Phân quyền hệ thống
@RestController
@RequestMapping("/api/phan-quyen")
@RequiredArgsConstructor
public class PhanQuyenController {

    private final PhanQuyenRepository repository;

    @GetMapping
    public List<PhanQuyen> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhanQuyen> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PhanQuyen create(@RequestBody PhanQuyen entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhanQuyen> update(@PathVariable Integer id, @RequestBody PhanQuyen entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setPqId(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
