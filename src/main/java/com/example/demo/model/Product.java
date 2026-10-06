package com.example.demo.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Product kế thừa MenuItem (inheritance).
 * Dùng Lombok: @Data tự sinh getter/setter/toString/equals/hashCode
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
public class Product extends MenuItem {
    private int quantity;
    private int categoryId;

    public Product(Integer id, String name, String description, double price, int quantity, int categoryId) {
        super(id, name, description, price);
        this.quantity = quantity;
        this.categoryId = categoryId;
    }
}
