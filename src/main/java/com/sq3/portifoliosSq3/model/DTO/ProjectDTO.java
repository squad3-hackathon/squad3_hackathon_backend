package com.sq3.portifoliosSq3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Date;

public record ProjectDTO(


        Long id,

        @NotNull
        @NotBlank
        String title,
        @NotNull
        @NotBlank
        @Size(max = 1000)
        String description,

        @Pattern(regexp = "^(http|https)://.*|$", message = "URL informada inválida.")
        String link,

        @NotNull
        String imageBase64,

        Date creationDate,

        String name
) {
}
