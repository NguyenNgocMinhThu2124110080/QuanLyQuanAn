package com.example.demo.controller;

import com.example.demo.entity.Kho;
import com.example.demo.repository.KhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: Quản lý Danh mục Kho bãi
@RestController
@RequestMapping("/api/kho")
@RequiredArgsConstructor
public class KhoController {

    private final KhoRepository repository;

    @GetMapping
    public List<Kho> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Kho> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Kho create(@RequestBody Kho entity) {
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Kho> update(@PathVariable Integer id, @RequestBody Kho entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setKhoId(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
