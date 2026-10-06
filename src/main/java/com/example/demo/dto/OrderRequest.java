package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class OrderRequest {

    @NotNull(message = "tableId không được để trống")
    @Positive(message = "tableId phải lớn hơn 0")
    private Integer tableId;

    @NotBlank(message = "Tên nhân viên không được để trống")
    private String staffName;

    private LocalDate orderDate;

    @NotNull(message = "Tổng tiền không được để trống")
    @Positive(message = "Tổng tiền phải lớn hơn 0")
    private Double totalAmount;

    @NotBlank(message = "Trạng thái không được để trống")
    private String status;
}
