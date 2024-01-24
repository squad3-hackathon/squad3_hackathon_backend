package com.sq3.portifoliosSq3.service;

import com.sq3.portifoliosSq3.repository.ProjectRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {

        this.projectRepository = projectRepository;
    }
}
