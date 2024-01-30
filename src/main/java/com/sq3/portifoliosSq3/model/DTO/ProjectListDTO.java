package com.sq3.portifoliosSq3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProjectListDTO(


        Long id,
        String imageBase64,

        String creationDate,

        String name
) {
}
