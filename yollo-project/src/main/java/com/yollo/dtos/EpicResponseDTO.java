package com.yollo.dtos;

import java.time.LocalDateTime;

public record EpicResponseDTO(
        Long id ,
        String title,
        Long productBacklogId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
