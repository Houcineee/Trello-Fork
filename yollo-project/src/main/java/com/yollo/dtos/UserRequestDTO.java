package com.yollo.dtos;

import com.yollo.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotNull(message = "Username must not be empty")
        String username,
        @NotNull(message = "email must not be empty")
        String email,
        @NotNull(message = "password must not be empty")
        String password,
        @NotNull(message = "role must not be empty")
        UserRole role
) {
}
