package com.yollo.dtos;

import com.yollo.models.enums.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequestDTO(
        @NotBlank(message = "Username must not be empty")
        String username,
        @NotBlank(message = "email must not be empty")
        String email,
        @NotBlank(message = "password must not be empty")
        String password,
        @NotNull(message = "role must not be empty")
        UserRole role
) {
}
