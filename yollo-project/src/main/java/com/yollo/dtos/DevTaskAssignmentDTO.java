package com.yollo.dtos;


// used in a specific patch operation to assign a task to a developer
public record DevTaskAssignmentDTO(
        Long developerId
) {
}
