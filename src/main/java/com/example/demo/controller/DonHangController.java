package com.example.demo.controller;
import com.example.demo.entity.DonHang;
import com.example.demo.repository.DonHangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@RestController
@RequestMapping("/api/don-hang")
public class DonHangController {
    @Autowired
    private DonHangRepository repository;

    @GetMapping
    public List<DonHang> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public DonHang create(@RequestBody DonHang entity) {
        return repository.save(entity);
    }
}
