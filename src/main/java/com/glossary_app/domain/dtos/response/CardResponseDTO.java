package com.glossary_app.domain.dtos.response;

import java.time.Instant;

public record CardResponseDTO(String frontText, String backText, Instant createdDate){}