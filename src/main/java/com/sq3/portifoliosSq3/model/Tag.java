package com.sq3.portifoliosSq3.model;

import com.sq3.portifoliosSq3.model.DTO.TagDTO;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Tag(TagDTO tagDTO){
        this.id = tagDTO.id();
        this.name = tagDTO.name();
    }
}


