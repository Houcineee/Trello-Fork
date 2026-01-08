package com.yollo.dtos;

import java.time.LocalDateTime;

public record SprintRequestDTO(
    String sprintName,
    LocalDateTime startedAt,
    LocalDateTime  finishedAt,
    Boolean isActive
) {
}
