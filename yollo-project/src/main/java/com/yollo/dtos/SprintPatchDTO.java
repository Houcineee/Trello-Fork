package com.yollo.dtos;

import java.time.LocalDateTime;

public record SprintPatchDTO(
        String sprintName,
        LocalDateTime startedAt,
        LocalDateTime  finishedAt,
        Boolean isActive
) {
}


