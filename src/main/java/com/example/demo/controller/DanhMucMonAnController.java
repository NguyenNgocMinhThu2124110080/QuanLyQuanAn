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
    @Autowired
    private DanhMucMonAnRepository repository;

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
        if (!repository.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build();
        entity.setMaDanhmuc(id);
        return org.springframework.http.ResponseEntity.ok(repository.save(entity));
    }

    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!repository.existsById(id)) return org.springframework.http.ResponseEntity.notFound().build();
        repository.deleteById(id);
        return org.springframework.http.ResponseEntity.ok().build();
    }
}
