package com.glossary_app.domain.dtos.response;

import java.time.Instant;

public record UserResponseDTO(String userName, String email, Instant createdDate) {}
