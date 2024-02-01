package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.service.TagService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;
    public TagController(TagService tagService){

        this.tagService = tagService;
    }
}