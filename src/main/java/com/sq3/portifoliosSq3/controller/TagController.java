package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.model.DTO.TagDTO;
import com.sq3.portifoliosSq3.model.Tag;
import com.sq3.portifoliosSq3.model.converter.TagConverter;
import com.sq3.portifoliosSq3.service.TagService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagConverter tagConverter;

    @Autowired
    private TagService tagService;

    @GetMapping
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @GetMapping("/{id}")
    public TagDTO getTagById(@PathVariable Long id) {
        return tagService.getTagById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Tag> saveTag(@Valid @RequestBody TagDTO tag) {
        return tagService.create(tag);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag novaTag) {
        Tag tagAtualizada = tagService.atualizar(id, novaTag);
        return ResponseEntity.ok(tagAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
    }

}
