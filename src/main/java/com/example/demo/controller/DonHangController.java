package com.example.demo.controller;

import com.example.demo.dto.DonHangChiTietResponse;
import com.example.demo.dto.TaoDonHangRequest;
import com.example.demo.dto.ThemMonVaoDonRequest;
import com.example.demo.entity.ChiTietDonHang;
import com.example.demo.entity.DonHang;
import com.example.demo.repository.DonHangRepository;
import com.example.demo.service.DonHangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// Buổi 4: API Quản lý Vòng đời Đơn hàng, Đặt món, Tự trừ kho & Chặn sửa/xóa đơn đã thanh toán
@RestController
@RequestMapping("/api/don-hang")
public class DonHangController {

    private final DonHangRepository donHangRepository;
    private final DonHangService donHangService;

    public DonHangController(DonHangRepository donHangRepository, DonHangService donHangService) {
        this.donHangRepository = donHangRepository;
        this.donHangService = donHangService;
    }

    @PostMapping("/mo-ban-tao-don")
    public ResponseEntity<?> moBanVaTaoDon(@RequestBody TaoDonHangRequest request) {
        try {
            DonHang dh = donHangService.taoDonHang(request);
            return ResponseEntity.ok(dh);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/them-mon")
    public ResponseEntity<?> themMonVaoDon(@RequestBody ThemMonVaoDonRequest request) {
        try {
            ChiTietDonHang ctdh = donHangService.themMonVaoDon(request);
            return ResponseEntity.ok(ctdh);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}/trang-thai")
    public ResponseEntity<?> capNhatTrangThai(@PathVariable Integer id, @RequestParam String trangThai) {
        try {
            DonHang dh = donHangService.capNhatTrangThaiDon(id, trangThai);
            return ResponseEntity.ok(dh);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/huy-mon/{maCtdh}")
    public ResponseEntity<?> huyMonKhoiDon(@PathVariable Integer maCtdh) {
        try {
            donHangService.xoaMonKhoiDon(maCtdh);
            return ResponseEntity.ok("Đã huỷ món và cập nhật lại tổng tiền đơn hàng!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{maDon}/chi-tiet-hoa-don")
    public ResponseEntity<?> layChiTietHoaDon(@PathVariable Integer maDon) {
        try {
            DonHangChiTietResponse res = donHangService.layChiTietHoaDon(maDon);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{maDon}/huy-don")
    public ResponseEntity<?> huyDonHang(@PathVariable Integer maDon) {
        try {
            donHangService.huyDonHang(maDon);
            return ResponseEntity.ok("Đã huỷ đơn hàng và giải phóng bàn ăn!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<DonHang> getAll() {
        return donHangRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return donHangRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/trang-thai/{trangThai}")
    public List<DonHang> layTheoTrangThai(@PathVariable String trangThai) {
        return donHangRepository.findByTrangThai(trangThai);
    }

    /**
     * Ràng buộc nghiệp vụ: Không được phép xóa đơn hàng đã thanh toán
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            donHangService.xoaDonHang(id);
            return ResponseEntity.ok("Đã xóa đơn hàng thành công!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
