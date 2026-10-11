package com.example.demo.controller;
import com.example.demo.entity.CongThucMon;
import com.example.demo.repository.CongThucMonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: NGUYỄN NGỌC MINH THƯ
@RestController
@RequestMapping("/api/cong-thuc-mon")
public class CongThucMonController {
    @Autowired
    private CongThucMonRepository repository;

    @GetMapping
    public List<CongThucMon> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public CongThucMon create(@RequestBody CongThucMon entity) {
        return repository.save(entity);
    }
}
