package com.travel.backend.service;

import com.travel.backend.dto.LoginRequest;
import com.travel.backend.dto.RegisterRequest;
import com.travel.backend.entity.User;

public interface UserService {
    User login(LoginRequest request);
    User register(RegisterRequest request);
} 