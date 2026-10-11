package com.example.demo.controller;

import com.example.demo.entity.MonAn;
import com.example.demo.repository.MonAnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Buổi 3: Quản lý Danh mục Món ăn, Tìm kiếm & Lọc món
@RestController
@RequestMapping("/api/mon-an")
@RequiredArgsConstructor
public class MonAnController {

    private final MonAnRepository monAnRepository;

    @GetMapping("/tim-kiem")
    public List<MonAn> timKiemTheoTen(@RequestParam String ten) {
        return monAnRepository.findByTenMonContainingIgnoreCase(ten);
    }

    @GetMapping("/danh-muc/{maDanhmuc}")
    public List<MonAn> layTheoDanhMuc(@PathVariable Integer maDanhmuc) {
        return monAnRepository.findByDanhMuc_MaDanhmuc(maDanhmuc);
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<String> doiTrangThaiMon(@PathVariable Integer id, @RequestParam Integer trangThai) {
        return monAnRepository.findById(id).map(mon -> {
            mon.setTrangThai(trangThai);
            monAnRepository.save(mon);
            return ResponseEntity.ok("Cập nhật trạng thái món thành công!");
        }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<MonAn> getAll() {
        return monAnRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonAn> getById(@PathVariable Integer id) {
        return monAnRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MonAn create(@RequestBody MonAn monAn) {
        if (monAn.getTrangThai() == null) monAn.setTrangThai(1);
        return monAnRepository.save(monAn);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MonAn> update(@PathVariable Integer id, @RequestBody MonAn monAn) {
        if (!monAnRepository.existsById(id)) return ResponseEntity.notFound().build();
        monAn.setMaMon(id);
        return ResponseEntity.ok(monAnRepository.save(monAn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!monAnRepository.existsById(id)) return ResponseEntity.notFound().build();
        monAnRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
