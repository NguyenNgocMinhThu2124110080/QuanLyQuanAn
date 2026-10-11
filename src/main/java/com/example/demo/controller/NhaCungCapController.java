package com.example.demo.controller;

import com.example.demo.entity.NhaCungCap;
import com.example.demo.repository.NhaCungCapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: Quản lý Danh mục Nhà Cung Cấp
@RestController
@RequestMapping("/api/nha-cung-cap")
@RequiredArgsConstructor
public class NhaCungCapController {

    private final NhaCungCapRepository repository;

    @GetMapping
    public List<NhaCungCap> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NhaCungCap> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NhaCungCap create(@RequestBody NhaCungCap entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhaCungCap> update(@PathVariable Integer id, @RequestBody NhaCungCap entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setNccId(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
