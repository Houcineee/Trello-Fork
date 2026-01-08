package com.yollo.dtos;

import java.time.LocalDateTime;

public record ProductResponseDTO(
        Long id,
        String projectName,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
