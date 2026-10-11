package com.example.demo.controller;

import com.example.demo.entity.CongThucMon;
import com.example.demo.entity.CongThucMonId;
import com.example.demo.repository.CongThucMonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 3: Quản lý Công thức & Định lượng nguyên liệu cho từng Món ăn
@RestController
@RequestMapping("/api/cong-thuc-mon")
@RequiredArgsConstructor
public class CongThucMonController {

    private final CongThucMonRepository repository;

    @GetMapping
    public List<CongThucMon> getAll() {
        return repository.findAll();
    }

    @GetMapping("/mon/{maMon}")
    public List<CongThucMon> layTheoMonAn(@PathVariable Integer maMon) {
        return repository.findById_MaMon(maMon);
    }

    @PostMapping
    public CongThucMon create(@RequestBody CongThucMon entity) {
        return repository.save(entity);
    }

    @DeleteMapping("/xoa")
    public ResponseEntity<Void> delete(@RequestParam Integer maMon, @RequestParam Integer nlId) {
        CongThucMonId id = new CongThucMonId();
        id.setMaMon(maMon);
        id.setNlId(nlId);

        if (!repository.existsById(id)) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
