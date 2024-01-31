package com.sq3.portifoliosSq3.controller;

import com.sq3.portifoliosSq3.exceptions.RecNotFoundException;
import com.sq3.portifoliosSq3.model.DTO.ProjectDTO;
import com.sq3.portifoliosSq3.model.DTO.ProjectListDTO;
import com.sq3.portifoliosSq3.model.DTO.ResponseDTO;
import com.sq3.portifoliosSq3.model.Project;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.service.AuthenticatedUserService;
import com.sq3.portifoliosSq3.service.ProjectService;
import com.sq3.portifoliosSq3.service.TagService;
import com.sq3.portifoliosSq3.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    public ProjectController(ProjectService projectService)
    {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectDTO projectDTO) {

        return ResponseEntity.ok(projectService.createProject(projectDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {

        return ResponseEntity.ok(projectService.updateProject(id, projectDTO));

    }

    @GetMapping
    public ResponseEntity<List<ProjectListDTO>> getAllProject() {

        return ResponseEntity.ok(projectService.getAllProject());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {


        return ResponseEntity.ok(projectService.getProjectDTOById(id));

    }

    @GetMapping("/tag")
    public ResponseEntity<List<ProjectDTO>> getProjectsByTags(
            @RequestParam List<String> tags,
            @RequestParam(required = false) boolean allUsers) {


        return ResponseEntity.ok(projectService.getProjectsByTags(tags, allUsers));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProject(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.deleteProject(id));
    }
}