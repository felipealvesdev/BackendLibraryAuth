package com.example.BackendLibraryWithAuthJavaSpring.dtos;

import com.example.BackendLibraryWithAuthJavaSpring.domain.enums.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
