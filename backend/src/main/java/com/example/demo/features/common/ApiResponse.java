package com.example.demo.features.common;

import java.util.Map;

public record ApiResponse<T>(
    boolean success,
    T data,
    String message,
    int statusCode,
    Map<String, String> errors
) {
    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, 200, null);
    }

    public static <T> ApiResponse<T> error(String message, int status) {
        return new ApiResponse<>(false, null, message, status, null);
    }
}