package com.glossary_app.domain.dtos.response;

import java.time.Instant;
import java.util.UUID;

public record UserResponseDTO(UUID userId, String userName, String email, Instant createdDate, Instant deletedDate) {}
