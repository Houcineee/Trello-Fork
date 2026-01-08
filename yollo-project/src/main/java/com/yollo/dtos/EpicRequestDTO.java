package com.yollo.dtos;

import jakarta.validation.constraints.NotBlank;

public record EpicRequestDTO(
    @NotBlank(message = "Title must not be blank")
    String title,
    @NotBlank(message = "Title must not be blank")
    String description
) {
}
