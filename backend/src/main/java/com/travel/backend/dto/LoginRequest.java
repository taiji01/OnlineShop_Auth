package com.travel.backend.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String emailOrPhone;
    private String password;
    private String role;
} 