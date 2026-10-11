package com.example.demo.dto;

import lombok.Data;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO yêu cầu đăng nhập hệ thống
@Data
public class LoginRequest {
    private String username;
    private String password;
}
