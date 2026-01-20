package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCardFrontTextRequestDTO(
        @NotBlank(message = "The card must have a front text.")
        @Size(min = 1, max = 50, message = "Text can only be up to 50 characters long.")
        String frontText
) {}