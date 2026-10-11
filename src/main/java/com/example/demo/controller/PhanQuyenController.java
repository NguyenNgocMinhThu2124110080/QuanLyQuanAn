package com.example.demo.controller;
import com.example.demo.entity.PhanQuyen;
import com.example.demo.repository.PhanQuyenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@RestController
@RequestMapping("/api/phan-quyen")
public class PhanQuyenController {
    @Autowired
    private PhanQuyenRepository repository;

    @GetMapping
    public List<PhanQuyen> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public PhanQuyen create(@RequestBody PhanQuyen entity) {
        return repository.save(entity);
    }
}
