package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.exceptions.RecNotFoundException;
import com.sq3.portifoliosSq3.model.DTO.ProjectDTO;
import com.sq3.portifoliosSq3.model.DTO.ProjectListDTO;
import com.sq3.portifoliosSq3.model.Project;
import com.sq3.portifoliosSq3.model.Tag;
import com.sq3.portifoliosSq3.model.User;
import com.sq3.portifoliosSq3.repository.ProjectRepository;
import com.sq3.portifoliosSq3.repository.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;
    private UserService userService;

    private TagService tagService;


    public ProjectService(ProjectRepository projectRepository,
                          UserService userService,
                          TagService tagService
                          ) {

        this.projectRepository = projectRepository;
        this.userService = userService;
        this.tagService = tagService;
    }

    @Transactional
    public Project createProject(ProjectDTO projectDTO) {

        Long userId = userService.getAuthenticatedUserId();

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RecNotFoundException("Usuário não encontrado com o id: " + userId));

        Set<Tag> tags = null;
        if (projectDTO.tagNames() != null && !projectDTO.tagNames().isEmpty()) {
            tags = tagService.getTagsByNames(projectDTO.tagNames());
            }

        byte[] imageData = null;
        if (projectDTO.imageBase64() != null && !projectDTO.imageBase64().isEmpty()) {
            imageData = Base64.getDecoder().decode(projectDTO.imageBase64());
        }

        Date data = new Date();


        Project project = new Project(projectDTO.title(), projectDTO.description(), projectDTO.link(), imageData, data, user, tags);


        return projectRepository.save(project);
    }

    @Transactional
    public Project updateProject(Long id, ProjectDTO projectDTO) {

        Long userId = userService.getAuthenticatedUserId();


        return projectRepository.findById(id).map(


                        existingProject -> {

                            if (!existingProject.getUserId().equals(userId)) {
                                throw new AccessDeniedException("Você não tem permissão para atualizar um projeto de outro usuário.");
                            }

                            existingProject.setTitle(projectDTO.title());
                            existingProject.setDescription(projectDTO.description());
                            existingProject.setLink(projectDTO.link());

                            if (projectDTO.imageBase64() != null && !projectDTO.imageBase64().isEmpty()) {
                                byte[] imageData = Base64.getDecoder().decode(projectDTO.imageBase64());
                                existingProject.setData(imageData);
                            }

                            Set <Tag> tags = null;
                            if (projectDTO.tagNames() != null && !projectDTO.tagNames().isEmpty()) {
                                tags = tagService.getTagsByNames(projectDTO.tagNames());
                            }
                            existingProject.setTags(tags);

                            return projectRepository.save(existingProject);
                        })
                .orElseThrow(() -> new RecNotFoundException("Projeto não encontrado com ID: " + id));

    }

    @Transactional
    public List<ProjectListDTO> getAllProject() {

        return projectRepository.findAll().stream()
                .map(project -> new ProjectListDTO(
                        project.getId(),
                        project.getData() != null ? Base64.getEncoder().encodeToString(project.getData()) : null,
                        recDateFormatted(project.getCreationDate()),
                        project.getUser().getName(),
                        project.getTags().stream().map(Tag::getName).collect(Collectors.toList())
                ))
                .collect(Collectors.toList());


    }

    @Transactional
    public ProjectDTO getProjectDTOById(Long id) {

        Long userId = userService.getAuthenticatedUserId();

        return projectRepository.findById(id)
                .map(project -> {
                    if (!project.getUserId().equals(userId)) {
                        throw new AccessDeniedException("Você não tem permissão para consultar um projeto de outro usuário.");
                    }
                    return new ProjectDTO(
                            project.getId(),
                            project.getTitle(),
                            project.getDescription(),
                            project.getLink(),
                            project.getData() != null ? Base64.getEncoder().encodeToString(project.getData()) : null,
                            project.getCreationDate(),
                            project.getUser().getId(),
                            project.getUser().getName(),
                            project.getTags().stream().map(Tag::getName).collect(Collectors.toList()));
                }) .orElseThrow(() -> new RecNotFoundException("Projeto não encontrado com ID: " + id));
    }


    @Transactional
    public List<ProjectDTO> getProjectsByTags(List<String> tags, Boolean user) {

        if (!user) {
            Long userId = userService.getAuthenticatedUserId();
            return projectRepository.findByUserIdAndTags(userId, tags).stream()
                    .map(project -> {
                        return new ProjectDTO(
                                project.getId(),
                                project.getTitle(),
                                project.getDescription(),
                                project.getLink(),
                                project.getData() != null ? Base64.getEncoder().encodeToString(project.getData()) : null,
                                project.getCreationDate(),
                                project.getUser().getId(),
                                project.getUser().getName(),
                                project.getTags().stream().map(Tag::getName).collect(Collectors.toList())
                        );
                    })
                    .collect(Collectors.toList());
        } else {
            return projectRepository.findByTags(tags).stream()
                    .map(project -> new ProjectDTO(
                            project.getId(),
                            project.getTitle(),
                            project.getDescription(),
                            project.getLink(),
                            project.getData() != null ? Base64.getEncoder().encodeToString(project.getData()) : null,
                            project.getCreationDate(),
                            project.getUser().getId(),
                            project.getUser().getName(),
                            project.getTags().stream().map(Tag::getName).collect(Collectors.toList())
                    ))
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public String deleteProject(Long id) {

        Long userId = userService.getAuthenticatedUserId();

        return projectRepository.findById(id).map(
                existingProject -> {

                    if (!existingProject.getUserId().equals(userId)) {
                        throw new AccessDeniedException("Você não tem permissão para deletar um projeto de outro usuário.");
                    }

                    projectRepository.deleteById(id);
                    return "Exclusão realizada com sucesso para o projeto com ID: " + id;

                }).orElseThrow(() -> new RecNotFoundException("Projeto não encontrado com ID: " + id));

    }


    private String recDateFormatted(Date creationDate) {

        if (creationDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
            LocalDate localDate = creationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            return localDate.format(formatter);
        } else {
            return null;
        }
    }

}
