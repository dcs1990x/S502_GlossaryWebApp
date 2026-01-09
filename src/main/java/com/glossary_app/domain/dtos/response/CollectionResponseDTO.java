package com.glossary_app.domain.dtos.response;

import java.util.List;
import java.util.UUID;

public record CollectionResponseDTO(UUID collectionId, String collectionName, List<CardResponseDTO> cardList) {}