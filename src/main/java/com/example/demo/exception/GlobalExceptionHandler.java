package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// PHẦN CỦA: ĐẶNG VŨ THANH PHÁT
// Lớp này dùng để bắt các lỗi chung trên toàn hệ thống và trả về mã lỗi HTTP đúng chuẩn.
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    
    // Phát có thể thêm các Exception khác như MethodArgumentNotValidException ở đây
}
