package com.yollo.dtos;

import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        String feedback,
        String status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
