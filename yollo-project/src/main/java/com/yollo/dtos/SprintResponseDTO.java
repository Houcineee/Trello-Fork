package com.yollo.dtos;

import java.time.LocalDateTime;

public record SprintResponseDTO(
        Long id,
        String sprintName,
        LocalDateTime startedAt,
        LocalDateTime  finishedAt,
        Boolean isActive,
        LocalDateTime updatedAt,
        LocalDateTime createdAt
) {

}
