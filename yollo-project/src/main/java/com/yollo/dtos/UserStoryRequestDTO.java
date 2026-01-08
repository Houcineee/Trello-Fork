package com.yollo.dtos;

import com.yollo.models.enums.UserStoryPriority;
import com.yollo.models.enums.UserStoryStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserStoryRequestDTO(
        @NotBlank(message = "Title must not be empty")
        String title,

        @NotBlank(message = "Description must not be empty")
        String description,

        @NotNull(message = "Story points must not be empty")
        Integer storyPoints,

        @NotNull(message = "Priority should be specified")
        UserStoryPriority priority,

        @NotNull(message = "status should be specified")
        UserStoryStatus status
) {
}
