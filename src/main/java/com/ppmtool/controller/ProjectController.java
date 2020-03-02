package com.ppmtool.controller;

import com.ppmtool.domain.Project;
import com.ppmtool.service.ProjectService;
import com.ppmtool.utils.BindingResultValidationErrorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:3000"})
public class ProjectController {

    private final ProjectService projectService;
    private final BindingResultValidationErrorUtil bindingResultValidationErrorUtil;

    @Autowired
    public ProjectController(ProjectService projectService, BindingResultValidationErrorUtil bindingResultValidationErrorUtil) {
        this.projectService = projectService;
        this.bindingResultValidationErrorUtil = bindingResultValidationErrorUtil;
    }

    @PostMapping(value = "/project", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        ResponseEntity<Map<String, String>> mapBindingResultErrorProcessed = bindingResultValidationErrorUtil.getBindingResultValidationError(bindingResult);
        if (mapBindingResultErrorProcessed != null) {
            return mapBindingResultErrorProcessed;
        } else {
//            Alternative, Note:I will not repeat this particular type of suggestion everywhere
//            return ResponseEntity.status(HttpStatus.CREATED).body(this.projectService.saveOrUpdateProject(project));
            return new ResponseEntity<Project>(projectService.saveOrUpdateProject(project), HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/project/{projectIdentifier}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Project> getProjectByProjectIdentifier(@PathVariable(value = "projectIdentifier") String projectIdentifier) {
        Project projectByProjectIdentifier = projectService.getProjectByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<Project>(projectByProjectIdentifier, HttpStatus.OK);
    }

    @GetMapping(value = "/project", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Project>> getAllProjects() {
        return new ResponseEntity<List<Project>>(projectService.getAllProjects(), HttpStatus.OK);
    }

    @DeleteMapping(value = "/project/{projectIdentifier}")
    public ResponseEntity<String> deleteProjectByProjectIdentifier(@PathVariable(value = "projectIdentifier") String projectIdentifier) {
        projectService.deleteProjectByProjectIdentifier(projectIdentifier);
        return new ResponseEntity<String>("Project with ID '" + projectIdentifier + "' was deleted successfully", HttpStatus.OK);
    }

}