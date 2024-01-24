package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.service.ProjectService;
import com.sq3.portifoliosSq3.service.TagService;
import com.sq3.portifoliosSq3.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    private UserService userService;

    private TagService tagService;

    public ProjectController(ProjectService projectService, UserService userService, TagService tagService) {

        this.projectService = projectService;
        this.userService = userService;
        this.tagService = tagService;
    }
}
