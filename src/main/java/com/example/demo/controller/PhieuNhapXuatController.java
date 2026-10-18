package com.example.demo.controller;

import com.example.demo.dto.NhapXuatKhoRequest;
import com.example.demo.entity.PhieuNhapXuat;
import com.example.demo.repository.PhieuNhapXuatRepository;
import com.example.demo.service.KhoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Buổi 4: API Lập phiếu Nhập/Xuất kho, Tự trừ kho theo món & Thống kê Kho
@RestController
@RequestMapping("/api/phieu-nhap-xuat")
public class PhieuNhapXuatController {

    private final PhieuNhapXuatRepository phieuNhapXuatRepository;
    private final KhoService khoService;

    public PhieuNhapXuatController(PhieuNhapXuatRepository phieuNhapXuatRepository, KhoService khoService) {
        this.phieuNhapXuatRepository = phieuNhapXuatRepository;
        this.khoService = khoService;
    }

    @PostMapping("/lap-phieu")
    public ResponseEntity<?> lapPhieuNhapXuat(@RequestBody NhapXuatKhoRequest request) {
        try {
            PhieuNhapXuat pnx = khoService.xuLyNhapXuat(request);
            return ResponseEntity.ok(pnx);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Tự động xuất kho trừ nguyên liệu khi chế biến món ăn
     */
    @PostMapping("/tru-kho-theo-mon")
    public ResponseEntity<?> truKhoTheoMon(
            @RequestParam Integer maMon,
            @RequestParam Integer soLuongMon,
            @RequestParam(required = false) Integer nvId,
            @RequestParam(required = false) Integer khoId) {
        try {
            List<PhieuNhapXuat> result = khoService.truKhoKhiBanMon(maMon, soLuongMon, nvId, khoId);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Thống kê tổng số lượng phiếu và số tiền Nhập / Xuất kho
     */
    @GetMapping("/thong-ke")
    public ResponseEntity<?> thongKeNhapXuat(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate tuNgay,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate denNgay) {
        return ResponseEntity.ok(khoService.thongKeNhapXuat(tuNgay, denNgay));
    }

    @GetMapping("/lich-su/nguyen-lieu/{nlId}")
    public List<PhieuNhapXuat> layLichSuTheoNguyenLieu(@PathVariable Integer nlId) {
        return khoService.lichSuTheoNguyenLieu(nlId);
    }

    @GetMapping
    public List<PhieuNhapXuat> getAll() {
        return phieuNhapXuatRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhieuNhapXuat> getById(@PathVariable Integer id) {
        return phieuNhapXuatRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (!phieuNhapXuatRepository.existsById(id)) return ResponseEntity.notFound().build();
        phieuNhapXuatRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
