package com.glossary_app.domain.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCollectionRequestDTO {

    @NotBlank
    @Size(min = 1, max = 10, message = "Collection name can only be up to 10 characters long.")
    private String collectionName;
}