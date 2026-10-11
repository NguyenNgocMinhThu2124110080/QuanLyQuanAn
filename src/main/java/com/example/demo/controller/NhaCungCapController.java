package com.example.demo.controller;
import com.example.demo.entity.NhaCungCap;
import com.example.demo.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@RestController
@RequestMapping("/api/nha-cung-cap")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapRepository repository;

    @GetMapping
    public List<NhaCungCap> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public NhaCungCap create(@RequestBody NhaCungCap entity) {
        return repository.save(entity);
    }
}
