package com.yollo.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
        @NotBlank(message = "Project name must not be empty")
        String projectName,
        @NotBlank(message = "Project description must not be empty")
        String description
) {
}
