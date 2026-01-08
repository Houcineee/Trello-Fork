package com.yollo.dtos;

import jakarta.validation.constraints.NotBlank;

public record EpicRequestDTO(
    @NotBlank(message = "Title must not be blank")
    String title,
    @NotBlank(message = "The Epic must belong to a Product Backlog")
    Long productBacklogId
) {
}
