package com.example.demo.controller;

import com.example.demo.entity.MonAn;
import com.example.demo.repository.MonAnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Mẫu API CRUD cho Món Ăn
@RestController
@RequestMapping("/api/mon-an")
@RequiredArgsConstructor
public class MonAnController {
    
    private final MonAnRepository monAnRepository;

    @GetMapping
    public List<MonAn> getAll() {
        return monAnRepository.findAll();
    }

    @PostMapping
    public MonAn create(@RequestBody MonAn monAn) {
        return monAnRepository.save(monAn);
    }

    @GetMapping("/{id}")
    public org.springframework.http.ResponseEntity<MonAn> getById(@PathVariable Integer id) {
        return monAnRepository.findById(id)
                .map(org.springframework.http.ResponseEntity::ok)
                .orElse(org.springframework.http.ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<MonAn> update(@PathVariable Integer id, @RequestBody MonAn monAn) {
        if (!monAnRepository.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build();
        monAn.setMaMon(id);
        return org.springframework.http.ResponseEntity.ok(monAnRepository.save(monAn));
    }

    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!monAnRepository.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build();
        monAnRepository.deleteById(id);
        return org.springframework.http.ResponseEntity.ok().build();
    }
}
