package com.yollo.dtos;

import com.yollo.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UserPatchDTO(
        String username,
        String email
) {
}
