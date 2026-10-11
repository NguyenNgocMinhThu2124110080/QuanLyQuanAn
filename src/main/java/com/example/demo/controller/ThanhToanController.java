package com.example.demo.controller;
import com.example.demo.entity.ThanhToan;
import com.example.demo.repository.ThanhToanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@RestController
@RequestMapping("/api/thanh-toan")
public class ThanhToanController {
    @Autowired
    private ThanhToanRepository repository;

    @GetMapping
    public List<ThanhToan> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ThanhToan create(@RequestBody ThanhToan entity) {
        return repository.save(entity);
    }
}
