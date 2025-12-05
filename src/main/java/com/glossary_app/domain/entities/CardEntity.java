package com.glossary_app.domain.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("cards")
public class CardEntity {

    @Id
    private UUID cardId;
    private UUID userId;

    @NotBlank
    @Size(max = 15, message = "Text should be maximum 15 characters long.")
    private String frontText;

    @NotBlank
    @Size(max = 15, message = "Text should be maximum 15 characters long.")
    private String backText;

    private Instant createdDate;
    private Instant deletedDate;
}