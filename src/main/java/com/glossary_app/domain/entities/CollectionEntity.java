package com.glossary_app.domain.entities;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Table("collections")
public class CollectionEntity {

    @Id
    private UUID collectionId;
    private UUID userId;

    @NotBlank
    @Size(max = 10, message = "Collection name should be maximum 10 characters long.")
    private String collectionName;

    private Instant createdDate;
    private Instant deletedDate;
}