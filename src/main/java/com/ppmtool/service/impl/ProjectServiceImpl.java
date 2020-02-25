package com.ppmtool.service.impl;

import com.ppmtool.domain.Project;
import com.ppmtool.exceptions.ProjectIdException;
import com.ppmtool.repository.ProjectRepository;
import com.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        Project savedProject;
        project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
        if (project.getId() == null) {
            try {
                savedProject = projectRepository.save(project);
            } catch (Exception e) {
                throw new ProjectIdException("A Project with '" + project.getProjectIdentifier().toUpperCase()
                        + "' as project identifier already exists in the database", HttpStatus.CONFLICT);
            }
        } else {
            try {
                savedProject = projectRepository.findById(project.getId()).get();
                project.setProjectIdentifier(savedProject.getProjectIdentifier());
                project.setCreatedDate(savedProject.getCreatedDate());
                savedProject = projectRepository.save(project);
            } catch (Exception e) {
                throw new ProjectIdException(
                        "A Project with '" + project.getId() + "' as project ID doesn't exists in the database",
                        HttpStatus.NOT_FOUND);
            }
        }
        return savedProject;
    }

    @Override
    public Project getProjectByProjectIdentifier(String projectIdentifier) {
        final Project byProjectIdentifier = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (byProjectIdentifier == null) {
            throw new ProjectIdException("A Project with '" + projectIdentifier.toUpperCase()
                    + "' as project identifier doesn't exists in the database", HttpStatus.NOT_FOUND);
        }
        return byProjectIdentifier;
    }

    @Override
    public Iterable<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public void deleteProjectByProjectIdentifier(String projectIdentifier) {
        final Project byProjectIdentifier = projectRepository.findByProjectIdentifier(projectIdentifier);
        if (byProjectIdentifier == null) {
            throw new ProjectIdException("A Project with '" + projectIdentifier.toUpperCase()
                    + "' as project identifier doesn't exists in the database", HttpStatus.NOT_FOUND);
        }
        projectRepository.delete(byProjectIdentifier);
    }

}