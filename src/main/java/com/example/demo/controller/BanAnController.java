package com.example.demo.controller;

import com.example.demo.entity.BanAn;
import com.example.demo.repository.BanAnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Buổi 3: Quản lý Danh mục Bàn ăn & Trạng thái Bàn
@RestController
@RequestMapping("/api/ban-an")
@RequiredArgsConstructor
public class BanAnController {

    private final BanAnRepository repository;

    @GetMapping("/ban-trong")
    public List<BanAn> layDanhSachBanTrong() {
        return repository.findByTrangThai("TRONG");
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<String> capNhatTrangThai(@PathVariable Integer id, @RequestParam String trangThai) {
        return repository.findById(id).map(ban -> {
            ban.setTrangThai(trangThai.toUpperCase());
            repository.save(ban);
            return ResponseEntity.ok("Cập nhật trạng thái bàn thành công!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<BanAn> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BanAn> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public BanAn create(@RequestBody BanAn entity) {
        if (entity.getTrangThai() == null) {
            entity.setTrangThai("TRONG");
        }
        return repository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BanAn> update(@PathVariable Integer id, @RequestBody BanAn entity) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setMaBan(id);
        return ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
