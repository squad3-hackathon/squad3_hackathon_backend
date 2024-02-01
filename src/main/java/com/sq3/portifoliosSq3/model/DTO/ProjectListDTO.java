package com.sq3.portifoliosSq3.model.DTO;

import java.util.List;

public record ProjectListDTO(
        Long id,
        String imageBase64,
        String creationDate,
        String name,
        List<String> tagNames
) {
}
