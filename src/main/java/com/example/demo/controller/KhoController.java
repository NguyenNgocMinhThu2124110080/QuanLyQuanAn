package com.example.demo.controller;
import com.example.demo.entity.Kho;
import com.example.demo.repository.KhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@RestController
@RequestMapping("/api/kho")
public class KhoController {
    @Autowired
    private KhoRepository repository;

    @GetMapping
    public List<Kho> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Kho create(@RequestBody Kho entity) {
        return repository.save(entity);
    }
}
