package com.example.demo.controller;

import com.example.demo.model.DiningTable;
import com.example.demo.service.DiningTableService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tables")
public class DiningTableController {
    private final DiningTableService service;
    public DiningTableController(DiningTableService service) { this.service = service; }
    @GetMapping public List<DiningTable> findAll() { return service.findAll(); }
    @GetMapping("/{id}") public DiningTable findById(@PathVariable int id) {
        return service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bàn ăn có id=" + id));
    }
    @PostMapping public DiningTable save(@RequestBody DiningTable table) { return service.save(table); }
    @PutMapping("/{id}") public DiningTable update(@PathVariable int id, @RequestBody DiningTable table) { return service.update(id, table); }
    @DeleteMapping("/{id}") public ResponseEntity<Map<String, String>> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Xóa thành công"));
    }
}
