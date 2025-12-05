package com.glossary_app.domain.dtos.response;

import java.util.List;

public record CollectionResponseDTO(String collectionName, List<CardResponseDTO> cardList) {}