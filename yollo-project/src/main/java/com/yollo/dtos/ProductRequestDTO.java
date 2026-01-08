package com.yollo.dtos;

import jakarta.validation.constraints.NotNull;

public record ProductRequestDTO(
        @NotNull(message = "Project name must not be empty")
        String projectName,
        @NotNull(message = "Project description must not be empty")
        String description
) {
}
