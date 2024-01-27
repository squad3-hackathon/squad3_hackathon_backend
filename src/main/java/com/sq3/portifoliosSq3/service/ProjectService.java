package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.model.Project;
import com.sq3.portifoliosSq3.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) { return projectRepository.save(project); }

    public Project updateProject(Project project) { return projectRepository.save(project); }

    @Transactional
    public List<Project> getAllProject() { return projectRepository.findAll(); }

    public Optional<Project> getProjectById(Long id) { return projectRepository.findById(id); }

    public void deleteProject(Long id) { projectRepository.deleteById(id); }
}
