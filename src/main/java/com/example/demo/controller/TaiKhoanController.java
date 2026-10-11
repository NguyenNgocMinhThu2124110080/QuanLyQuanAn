package com.example.demo.controller;

import com.example.demo.entity.TaiKhoan;
import com.example.demo.repository.TaiKhoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Mẫu API CRUD Cơ bản
@RestController
@RequestMapping("/api/tai-khoan")
@RequiredArgsConstructor
public class TaiKhoanController {
    
    private final TaiKhoanRepository taiKhoanRepository;

    @GetMapping
    public List<TaiKhoan> getAll() {
        return taiKhoanRepository.findAll();
    }

    @PostMapping
    public TaiKhoan create(@RequestBody TaiKhoan taiKhoan) {
        return taiKhoanRepository.save(taiKhoan);
    }
}
