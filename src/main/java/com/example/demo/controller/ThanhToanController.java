package com.example.demo.controller;

import com.example.demo.dto.ThanhToanRequest;
import com.example.demo.entity.ThanhToan;
import com.example.demo.repository.ThanhToanRepository;
import com.example.demo.service.ThanhToanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 3: API Quy trình Thanh toán Hoá đơn & Thu tiền
@RestController
@RequestMapping("/api/thanh-toan")
@RequiredArgsConstructor
public class ThanhToanController {

    private final ThanhToanRepository thanhToanRepository;
    private final ThanhToanService thanhToanService;

    @PostMapping("/thuc-hien")
    public ResponseEntity<?> thucHienThanhToan(@RequestBody ThanhToanRequest request) {
        try {
            ThanhToan tt = thanhToanService.thucHienThanhToan(request);
            return ResponseEntity.ok(tt);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/don-hang/{maDon}")
    public ResponseEntity<ThanhToan> timTheoDonHang(@PathVariable Integer maDon) {
        return thanhToanRepository.findByDonHang_MaDon(maDon)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ThanhToan> getAll() {
        return thanhToanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThanhToan> getById(@PathVariable Integer id) {
        return thanhToanRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ThanhToan create(@RequestBody ThanhToan entity) {
        return thanhToanRepository.save(entity);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ThanhToan> update(@PathVariable Integer id, @RequestBody ThanhToan entity) {
        if (!thanhToanRepository.existsById(id)) return ResponseEntity.notFound().build();
        entity.setMaTt(id);
        return ResponseEntity.ok(thanhToanRepository.save(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!thanhToanRepository.existsById(id)) return ResponseEntity.notFound().build();
        thanhToanRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
