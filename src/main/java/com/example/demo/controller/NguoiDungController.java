package com.example.demo.controller;
import com.example.demo.entity.NguoiDung;
import com.example.demo.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
@RestController
@RequestMapping("/api/nguoi-dung")
public class NguoiDungController {
    @Autowired
    private NguoiDungRepository repository;

    @GetMapping
    public List<NguoiDung> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public NguoiDung create(@RequestBody NguoiDung entity) {
        return repository.save(entity);
    }
}
