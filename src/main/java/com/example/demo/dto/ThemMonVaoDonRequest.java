package com.example.demo.dto;

import lombok.Data;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO yêu cầu thêm món ăn vào đơn hàng (gọi món)
@Data
public class ThemMonVaoDonRequest {
    private Integer maDon;
    private Integer maMon;
    private Integer soLuong;
}
