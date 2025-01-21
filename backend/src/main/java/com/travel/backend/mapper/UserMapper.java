package com.travel.backend.mapper;

import com.travel.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByEmailOrPhone(String emailOrPhone);
    User findByEmail(String email);
    User findByPhone(String phone);
    void insert(User user);
} 