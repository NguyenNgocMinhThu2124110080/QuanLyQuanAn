package com.example.demo.controller;
import com.example.demo.entity.DanhMucMonAn;
import com.example.demo.repository.DanhMucMonAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@RestController
@RequestMapping("/api/danh-muc-mon-an")
public class DanhMucMonAnController {
    private final DanhMucMonAnRepository repository;

    public DanhMucMonAnController(DanhMucMonAnRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<DanhMucMonAn> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public DanhMucMonAn create(@RequestBody DanhMucMonAn entity) {
        return repository.save(entity);
    }

    @GetMapping("/{id}")
    public org.springframework.http.ResponseEntity<DanhMucMonAn> getById(@PathVariable Integer id) {
        return repository.findById(id)
                .map(org.springframework.http.ResponseEntity::ok)
                .orElse(org.springframework.http.ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<DanhMucMonAn> update(@PathVariable Integer id, @RequestBody DanhMucMonAn entity) {
        return repository.findById(id).map(existing -> {
            if (entity.getTenDanhmuc() != null && !entity.getTenDanhmuc().trim().isEmpty()) {
                existing.setTenDanhmuc(entity.getTenDanhmuc().trim());
            }
            if (entity.getMoTa() != null) {
                existing.setMoTa(entity.getMoTa());
            }
            if (entity.getTrangThai() != null) {
                existing.setTrangThai(entity.getTrangThai());
            } else if (existing.getTrangThai() == null) {
                existing.setTrangThai(1);
            }
            return org.springframework.http.ResponseEntity.ok(repository.save(existing));
        }).orElse(org.springframework.http.ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build();
        repository.deleteById(id);
        return org.springframework.http.ResponseEntity.ok().build();
    }
}
