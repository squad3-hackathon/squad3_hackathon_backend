package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {

        this.tagRepository = tagRepository;
    }
}
