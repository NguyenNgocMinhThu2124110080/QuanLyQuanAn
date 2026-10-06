package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Lớp trừu tượng (abstract) đại diện cho một mục thực đơn.
 * Product kế thừa lớp này (áp dụng kế thừa - inheritance).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private double price;
}
