package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateCollectionNameRequestDTO(
        @NotBlank(message = "A name is required.")
        @Size(min = 1, max = 10, message = "Collection name must be between 1 and 8 characters long.")
        String newName
) {}