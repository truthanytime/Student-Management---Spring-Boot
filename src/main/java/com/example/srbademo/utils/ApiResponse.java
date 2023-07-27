package com.example.srbademo.utils;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private int status;
    private boolean success;
    private String message;
    private T body;

    // Constructors, getters, and setters

    public ApiResponse(){
        this.status = HttpServletResponse.SC_NOT_FOUND;
        this.success = false;
        this.message = null;
        this.body = null;
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(int status, boolean success, String message){
        this.status = status;
        this.success = success;
        this.message = message;
        this.body = null;
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(int status, boolean success, String message, T body){
        this.status = status;
        this.success = success;
        this.message = message;
        this.body = body;
        this.timestamp = LocalDateTime.now();
    }
}
