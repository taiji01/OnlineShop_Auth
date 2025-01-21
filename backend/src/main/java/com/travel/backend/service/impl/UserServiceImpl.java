package com.travel.backend.service.impl;

import com.travel.backend.dto.LoginRequest;
import com.travel.backend.dto.RegisterRequest;
import com.travel.backend.entity.User;
import com.travel.backend.mapper.UserMapper;
import com.travel.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @Override
    public User login(LoginRequest request) {
        User user = userMapper.findByEmailOrPhone(request.getEmailOrPhone());
        if (user != null && user.getPassword().equals(request.getPassword()) 
            && user.getRole().equals(request.getRole())) {
            return user;
        }
        return null;
    }
    
    @Override
    public User register(RegisterRequest request) {
        // 检查邮箱和手机号是否已存在
        if (request.getEmail() != null && userMapper.findByEmail(request.getEmail()) != null) {
            throw new RuntimeException("邮箱已被注册");
        }
        if (request.getPhone() != null && userMapper.findByPhone(request.getPhone()) != null) {
            throw new RuntimeException("手机号已被注册");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setRole(request.getRole());
        
        userMapper.insert(user);
        return user;
    }
} 