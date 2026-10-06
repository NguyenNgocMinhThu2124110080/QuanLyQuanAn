package com.example.demo.service;

import com.example.demo.model.DiningTable;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DiningTableRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiningTableService {
    private final DiningTableRepository repository;
    public DiningTableService(DiningTableRepository repository) { this.repository = repository; }
    public List<DiningTable> findAll() { return repository.findAll(); }
    public Optional<DiningTable> findById(int id) { return repository.findById(id); }
    public DiningTable save(DiningTable table) { table.setId(null); return repository.save(table); }
    public DiningTable update(int id, DiningTable table) {
        return repository.findById(id).map(existing -> {
            existing.setName(table.getName());
            existing.setSeats(table.getSeats());
            existing.setStatus(table.getStatus());
            return repository.save(existing);
        }).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy bàn ăn có id=" + id));
    }
    public void delete(int id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Không tìm thấy bàn ăn có id=" + id);
        }
        repository.deleteById(id);
    }
}
