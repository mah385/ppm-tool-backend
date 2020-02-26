package com.ppmtool.service;

import com.ppmtool.domain.Project;

import java.util.List;

public interface ProjectService {

    Project saveOrUpdateProject(Project project);

    Project getProjectByProjectIdentifier(String projectIdentifier);

    List<Project> getAllProjects();

    void deleteProjectByProjectIdentifier(String projectIdentifier);

}
