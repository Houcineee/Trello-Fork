package com.yollo.dtos;

import com.yollo.models.enums.UserStoryPriority;
import com.yollo.models.enums.UserStoryStatus;

import java.time.LocalDateTime;

public record UserStoryResponseDTO(
        Long id,
        String title,
        String description,
        Integer storyPoints,
        UserStoryPriority priority,
        UserStoryStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
