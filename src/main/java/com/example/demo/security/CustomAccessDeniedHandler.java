package com.example.demo.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Buổi 4: Xử lý mã lỗi 403 FORBIDDEN khi người dùng không đủ quyền hạn truy cập API
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("{\"status\":403,\"error\":\"Forbidden\",\"message\":\"Từ chối truy cập! Bạn không có quyền (Role) phù hợp để thực hiện hành động này.\"}");
    }
}
