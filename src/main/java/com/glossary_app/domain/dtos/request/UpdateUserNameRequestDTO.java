package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserNameRequestDTO(
        @NotBlank
        @Size(min = 1, max = 10)
        String newUserName) {}