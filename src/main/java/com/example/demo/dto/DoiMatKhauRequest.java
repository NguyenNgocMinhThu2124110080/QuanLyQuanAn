package com.example.demo.dto;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// DTO đổi mật khẩu tài khoản
public class DoiMatKhauRequest {
    private String username;
    private String matKhauCu;
    private String matKhauMoi;

    public DoiMatKhauRequest() {
    }

    public DoiMatKhauRequest(String username, String matKhauCu, String matKhauMoi) {
        this.username = username;
        this.matKhauCu = matKhauCu;
        this.matKhauMoi = matKhauMoi;
    }

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
