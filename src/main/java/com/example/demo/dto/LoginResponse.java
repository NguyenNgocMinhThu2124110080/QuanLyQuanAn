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
    private String token; // Chuỗi JWT Token để xác thực các request sau

    public static LoginResponse failure(String message) {
        LoginResponse res = new LoginResponse();
        res.setSuccess(false);
        res.setMessage(message);
        return res;
    }

    public static LoginResponse success(String username, String vaiTro, String hoTen, Integer userId, Integer nvId, String token) {
        LoginResponse res = new LoginResponse();
        res.setSuccess(true);
        res.setMessage("Đăng nhập thành công!");
        res.setUsername(username);
        res.setVaiTro(vaiTro);
        res.setHoTen(hoTen);
        res.setUserId(userId);
        res.setNvId(nvId);
        res.setToken(token);
        return res;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNvId() {
        return nvId;
    }

    public void setNvId(Integer nvId) {
        this.nvId = nvId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
