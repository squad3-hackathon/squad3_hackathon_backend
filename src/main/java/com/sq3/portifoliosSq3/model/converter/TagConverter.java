package com.sq3.portifoliosSq3.model.converter;

import com.sq3.portifoliosSq3.model.DTO.TagDTO;
import com.sq3.portifoliosSq3.model.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagConverter {

    /**
     * Metodo utilizado para cadastro da tag
     */
    public Tag convertDtoToEntity(TagDTO tagDTO) {
        return Tag.builder()
                .id(tagDTO.id())
                .name(tagDTO.name())
                .build();
        }

}
