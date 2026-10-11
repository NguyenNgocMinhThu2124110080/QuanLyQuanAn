package com.example.demo.controller;
import com.example.demo.entity.ChiTietDonHang;
import com.example.demo.repository.ChiTietDonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@RestController
@RequestMapping("/api/chi-tiet-don-hang")
public class ChiTietDonHangController {
    @Autowired
    private ChiTietDonHangRepository repository;

    @GetMapping
    public List<ChiTietDonHang> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ChiTietDonHang create(@RequestBody ChiTietDonHang entity) {
        return repository.save(entity);
    }
}
