package com.glossary_app.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTermRequestDTO {

    @NotBlank
    @Size(min = 1, max = 50, message = "Text can only be up to 50 characters long.")
    private String term;
}