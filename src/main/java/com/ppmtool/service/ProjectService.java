package com.ppmtool.service;

import com.ppmtool.domain.Project;

public interface ProjectService {

    Project saveOrUpdateProject(Project project);

    Project getProjectByProjectIdentifier(String projectIdentifier);

    Iterable<Project> getAllProjects();

}
