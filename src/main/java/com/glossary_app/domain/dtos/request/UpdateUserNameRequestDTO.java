package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateUserNameRequestDTO(
        @NotBlank
        @Size(min = 1, max = 10, message = "The new user name must be at least 1 and up to 10 characters long.")
        String newUserName) {}