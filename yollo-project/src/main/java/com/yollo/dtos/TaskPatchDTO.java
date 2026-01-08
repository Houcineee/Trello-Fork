package com.yollo.dtos;

import java.time.LocalDateTime;

public record TaskPatchDTO(
        String title,
        String description,
        String feedback,
        String status
) {
}
