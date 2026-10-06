package com.example.demo.repository;

import com.example.demo.model.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiningTableRepository extends JpaRepository<DiningTable, Integer> {
}
