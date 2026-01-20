package com.glossary_app.domain.dtos.response;

import java.util.Set;
import java.util.UUID;

public record CollectionResponseDTO(
        UUID collectionId,
        String collectionName,
        Set<CardResponseDTO> cards) {}