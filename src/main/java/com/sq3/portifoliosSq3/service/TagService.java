package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.model.Tag;
import com.sq3.portifoliosSq3.repository.TagRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {

        this.tagRepository = tagRepository;
    }
    public Set<Tag> getTagsByNames(List<String> tagNames) {
        return tagNames.stream()
                .map(name -> tagRepository.findByName(name)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(name);
                            return tagRepository.save(newTag);
                        }))
                .collect(Collectors.toSet());
    }
}