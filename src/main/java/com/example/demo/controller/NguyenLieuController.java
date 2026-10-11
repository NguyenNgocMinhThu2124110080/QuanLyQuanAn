package com.example.demo.controller;

import com.example.demo.entity.NguyenLieu;
import com.example.demo.repository.NguyenLieuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
// Mẫu API CRUD cho Nguyên Liệu
@RestController
@RequestMapping("/api/nguyen-lieu")
@RequiredArgsConstructor
public class NguyenLieuController {
    
    private final NguyenLieuRepository nguyenLieuRepository;

    @GetMapping
    public List<NguyenLieu> getAll() {
        return nguyenLieuRepository.findAll();
    }

    @PostMapping
    public NguyenLieu create(@RequestBody NguyenLieu nguyenLieu) {
        return nguyenLieuRepository.save(nguyenLieu);
    }
}
