package com.ppmtool.service.impl;

import com.ppmtool.domain.Project;
import com.ppmtool.repository.ProjectRepository;
import com.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project saveOrUpdateProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getProjectByProjectIdentifier(String projectIdentifier) {
        return projectRepository.findByProjectIdentifier(projectIdentifier);
    }

    @Override
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
