package com.yollo.dtos;

import com.yollo.models.enums.TaskStatus;
import jakarta.validation.constraints.NotEmpty;

public record TaskRequestDTO(
        @NotEmpty(message = "Title cannot be empty")
        String title,
        @NotEmpty(message = "Description cannot be empty")
        String description,
        String feedback,
        @NotEmpty(message = "Status cannot be empty")
        TaskStatus status
) {
}
