package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO đổi mật khẩu tài khoản
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoiMatKhauRequest {
    private String username;
    private String matKhauCu;
    private String matKhauMoi;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMatKhauCu() {
        return matKhauCu;
    }

    public void setMatKhauCu(String matKhauCu) {
        this.matKhauCu = matKhauCu;
    }

    public String getMatKhauMoi() {
        return matKhauMoi;
    }

    public void setMatKhauMoi(String matKhauMoi) {
        this.matKhauMoi = matKhauMoi;
    }
}
