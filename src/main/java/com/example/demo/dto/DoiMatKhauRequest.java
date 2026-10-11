package com.example.demo.dto;

import lombok.Data;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO đổi mật khẩu tài khoản
@Data
public class DoiMatKhauRequest {
    private String username;
    private String matKhauCu;
    private String matKhauMoi;
}
