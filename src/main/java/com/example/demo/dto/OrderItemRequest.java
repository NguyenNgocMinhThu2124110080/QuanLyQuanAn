package com.example.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {

    @NotNull(message = "orderId không được để trống")
    @Positive(message = "orderId phải lớn hơn 0")
    private Integer orderId;

    @NotNull(message = "productId không được để trống")
    @Positive(message = "productId phải lớn hơn 0")
    private Integer productId;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 1, message = "Số lượng phải >= 1")
    private Integer quantity;

    @NotNull(message = "Đơn giá không được để trống")
    @Positive(message = "Đơn giá phải lớn hơn 0")
    private Double unitPrice;

    @NotBlank(message = "Trạng thái chế biến không được để trống")
    private String cookingStatus;
}
