package com.example.demo.dto;

import lombok.Data;

// PHẦN CỦA: NGUYỄN XUÂN QUÝ
// DTO yêu cầu mở bàn và tạo đơn hàng mới
@Data
public class TaoDonHangRequest {
    private Integer maBan;
    private Integer maNv;
}
