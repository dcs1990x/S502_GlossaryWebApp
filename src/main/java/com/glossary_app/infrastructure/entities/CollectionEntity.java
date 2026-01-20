package com.glossary_app.infrastructure.entities;

import org.springframework.data.annotation.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("collections")
public class CollectionEntity {

    @Id
    private UUID collectionId;
    private UUID userId;

    @NotBlank
    @Size(max = 10, message = "Collection name should be maximum 10 characters long.")
    private String collectionName;
}