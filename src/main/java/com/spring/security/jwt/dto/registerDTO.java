package com.spring.security.jwt.dto;

import com.spring.security.jwt.model.UserRole;

public record registerDTO(String userName, String password, UserRole role) {
}
