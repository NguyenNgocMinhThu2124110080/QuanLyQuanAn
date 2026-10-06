package com.example.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * Xử lý ngoại lệ tập trung cho toàn bộ ứng dụng.
 *
 * Các phương thức:
 * 1. handleNotFound       — bắt ResourceNotFoundException → HTTP 404
 * 2. handleBadRequest     — bắt IllegalArgumentException  → HTTP 400
 * 3. handleValidation     — bắt lỗi @Valid (thiếu tên, giá, ...) → HTTP 400
 *
 * Tầng gọi exception: SERVICE gọi/ném ResourceNotFoundException & IllegalArgumentException.
 * Controller dùng @Valid để kích hoạt MethodArgumentNotValidException.
 * ApiExceptionHandler (tầng này) bắt tất cả và trả JSON chuẩn.
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /** Bắt ResourceNotFoundException — tài nguyên không tồn tại (404) */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException exception,
                                                   HttpServletRequest request) {
        return buildError(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    /** Bắt IllegalArgumentException — tham số không hợp lệ (400) */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleBadRequest(IllegalArgumentException exception,
                                                     HttpServletRequest request) {
        return buildError(HttpStatus.BAD_REQUEST, exception.getMessage(), request);
    }

    /**
     * Bắt lỗi validation từ @Valid trên DTO — thiếu tên/giá/số lượng/categoryId (400).
     * Gom tất cả lỗi validation thành một chuỗi để trả về client.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception,
                                                     HttpServletRequest request) {
        String message = exception.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return buildError(HttpStatus.BAD_REQUEST, message, request);
    }

    /** Tạo ApiError chuẩn để trả về client */
    private ResponseEntity<ApiError> buildError(HttpStatus status, String message,
                                                HttpServletRequest request) {
        return ResponseEntity.status(status).body(new ApiError(
                Instant.now(), status.value(), status.getReasonPhrase(),
                message, request.getRequestURI()));
    }

    /** Record đại diện cho cấu trúc JSON lỗi trả về client */
    public record ApiError(Instant timestamp, int status, String error,
                           String message, String path) {
    }
}