package com.glossary_app.domain.dtos.response;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record UserWithCollectionsResponseDTO(
        UUID userId,
        String userName,
        String email,
        Instant createdDate,
        Instant deletedDate,
        List<CollectionResponseDTO> collections) {}