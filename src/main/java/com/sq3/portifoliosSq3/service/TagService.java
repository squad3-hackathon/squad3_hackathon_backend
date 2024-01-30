package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.model.DTO.TagDTO;
import com.sq3.portifoliosSq3.model.Tag;
import com.sq3.portifoliosSq3.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {

        this.tagRepository = tagRepository;
    }
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    public TagDTO getTagById(Long id) {

        Tag tag = tagRepository.findById(id).orElse(null);
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public ResponseEntity<Tag> create(TagDTO tagDTO) {
        Tag tag = new Tag(tagDTO);
        tagRepository.save(tag);
       return ResponseEntity.ok(tag);
    }

    @Transactional
    public Tag atualizar(Long id, Tag novaTag) {
        Tag tagExistente = tagRepository.findById(id)
                .orElseThrow(null);
        tagExistente.setName(novaTag.getName());
        return tagRepository.save(tagExistente);
    }
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
