package com.glossary_app.domain.dtos.response;

public record CardResponseDTO(
        Long cardId,
        String frontText,
        String backText){}