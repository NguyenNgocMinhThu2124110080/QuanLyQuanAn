package com.example.demo.controller;
import com.example.demo.entity.PhieuNhapXuat;
import com.example.demo.repository.PhieuNhapXuatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@RestController
@RequestMapping("/api/phieu-nhap-xuat")
public class PhieuNhapXuatController {
    @Autowired
    private PhieuNhapXuatRepository repository;

    @GetMapping
    public List<PhieuNhapXuat> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public PhieuNhapXuat create(@RequestBody PhieuNhapXuat entity) {
        return repository.save(entity);
    }
}
