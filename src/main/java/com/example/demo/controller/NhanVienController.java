package com.example.demo.controller;
import com.example.demo.entity.NhanVien;
import com.example.demo.repository.NhanVienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@RestController
@RequestMapping("/api/nhan-vien")
public class NhanVienController {
    @Autowired
    private NhanVienRepository repository;

    @GetMapping
    public List<NhanVien> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public NhanVien create(@RequestBody NhanVien entity) {
        return repository.save(entity);
    }
}
