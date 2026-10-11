package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO phản hồi kết quả đăng nhập và quyền hạn
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private boolean success;
    private String message;
    private String username;
    private String vaiTro;      // Tên phân quyền: ADMIN, QUAN_LY, NHAN_VIEN_PHUC_VU, DAU_BEP
    private String hoTen;       // Tên người dùng thực tế
    private Integer userId;
    private Integer nvId;
}
