package com.yollo.dtos;

import com.yollo.models.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record UserPatchDTO(
        String username,
        @Email(message ="invalid email format")
        String email
) {
}
