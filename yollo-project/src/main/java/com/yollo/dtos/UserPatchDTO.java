package com.yollo.dtos;

import jakarta.validation.constraints.Email;

public record UserPatchDTO(
        String username,
        @Email(message ="invalid email format")
        String email
) {
}
