package com.example.demo.exception;

/**
 * Exception tùy chỉnh — ném ra khi không tìm thấy tài nguyên trong DB.
 * Service ném exception này, ApiExceptionHandler bắt và trả HTTP 404.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}