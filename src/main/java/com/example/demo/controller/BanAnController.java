package com.example.demo.controller;
import com.example.demo.entity.BanAn;
import com.example.demo.repository.BanAnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN XUÂN QUÝ
@RestController
@RequestMapping("/api/ban-an")
public class BanAnController {
    @Autowired
    private BanAnRepository repository;

    @GetMapping
    public List<BanAn> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public BanAn create(@RequestBody BanAn entity) {
        return repository.save(entity);
    }
}
