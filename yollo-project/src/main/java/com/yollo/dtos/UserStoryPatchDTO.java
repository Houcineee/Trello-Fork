package com.yollo.dtos;

import com.yollo.models.enums.UserStoryPriority;
import com.yollo.models.enums.UserStoryStatus;

public record UserStoryPatchDTO(
        String title,
        String description,
        Integer storyPoints,
        UserStoryPriority priority,
        UserStoryStatus status

) {
}
