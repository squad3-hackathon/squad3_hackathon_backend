package com.sq3.portifoliosSq3.controller;


import com.sq3.portifoliosSq3.exceptions.models.RecNotFoundException;
import com.sq3.portifoliosSq3.model.DTO.ProjectDTO;
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


import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private ProjectService projectService;

    private UserService userService;

    private TagService tagService;

    private AuthenticatedUserService authenticatedUserService;

    public ProjectController(ProjectService projectService, UserService userService, TagService tagService, AuthenticatedUserService authenticatedUserService) {

        this.projectService = projectService;
        this.userService = userService;
        this.tagService = tagService;
        this.authenticatedUserService = authenticatedUserService;
    }

    @PostMapping
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectDTO projectDTO) {

        Long userId = authenticatedUserService.getAuthenticatedUserId();
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RecNotFoundException("Usuário não encontrado com o id: " + userId));

        byte[] imageData = null;
        if (projectDTO.imageBase64() != null && !projectDTO.imageBase64().isEmpty()) {
            imageData = Base64.getDecoder().decode(projectDTO.imageBase64());
        }

        Project project = new Project(projectDTO.title(), projectDTO.description(), projectDTO.link(), imageData, user);
        Project savedProject = projectService.createProject(project);

        return ResponseEntity.ok(savedProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Project> updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDTO projectDTO) {

        Long userId = authenticatedUserService.getAuthenticatedUserId();

        return projectService.getProjectById(id)
                .map(existingProject -> {
                    if (!existingProject.getUser().getId().equals(userId)) {
                        throw new AccessDeniedException("Você não tem permissão para atualizar um projeto de outro usuário.");
                    }

                    existingProject.setTitle(projectDTO.title());
                    existingProject.setDescription(projectDTO.description());
                    existingProject.setLink(projectDTO.link());

                    byte[] imageData = null;
                    if (projectDTO.imageBase64() != null && !projectDTO.imageBase64().isEmpty()) {
                        imageData = Base64.getDecoder().decode(projectDTO.imageBase64());
                        existingProject.setData(imageData);
                    }

                    Project updateProject = projectService.updateProject(existingProject);
                    return ResponseEntity.ok(updateProject);
                })
                .orElseThrow(() -> new RecNotFoundException("Projeto não encontrado com ID: " + id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProject() {
        List<ProjectDTO> projects = projectService.getAllProject().stream()
                .map(project -> new ProjectDTO(
                        project.getId(),
                        project.getTitle(),
                        project.getDescription(),
                        project.getLink(),
                        project.getData() != null ? Base64.getEncoder().encodeToString(project.getData()) : null,
                        project.getUser().getName()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectDTO> getProjectById(@PathVariable Long id) {


        return projectService.getProjectById(id)
                .map(project -> {
                    String imageDataBase64 = project.getData() != null
                          ? Base64.getEncoder().encodeToString(project.getData())
                          : null;
                    ProjectDTO projectDTO = new ProjectDTO(
                            project.getId(),
                            project.getTitle(),
                            project.getDescription(),
                            project.getLink(),
                            imageDataBase64,
                            project.getUser().getName()
                    );
                    return ResponseEntity.ok(projectDTO);
                })
                .orElseThrow(() -> new RecNotFoundException("ID informado não localizado: " + id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProject(@PathVariable Long id) {
        projectService.getProjectById(id).map(project -> {
            if (!project.getUser().getId().equals(authenticatedUserService.getAuthenticatedUserId())){
                throw new AccessDeniedException("Você não tem permissão para deletar um projeto de outro usuário.");
            }
            projectService.deleteProject(id);
            return ResponseEntity.ok();

        }).orElseThrow(() -> new RecNotFoundException("Projeto não encotrado com ID: " + id));

        return ResponseEntity.ok(new ResponseDTO("Projeto excluido com sucesso."));
    }
}
