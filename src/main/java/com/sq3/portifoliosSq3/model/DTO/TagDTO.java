package com.sq3.portifoliosSq3.model.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TagDTO(

        Long id,

        @NotBlank
        String name){

}
