package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Xử lý mã lỗi 401 UNAUTHORIZED khi chưa đăng nhập hoặc token không hợp lệ
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Bạn chưa đăng nhập hoặc Token JWT không hợp lệ/hết hạn! Vui lòng gửi kèm Bearer Token hợp lệ.\"}");
    }
}
