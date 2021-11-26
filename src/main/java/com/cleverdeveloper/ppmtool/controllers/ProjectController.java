package com.cleverdeveloper.ppmtool.controllers;

import com.cleverdeveloper.ppmtool.domain.Project;
import com.cleverdeveloper.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 11/26/2021 10:39 PM
*/

@RestController
@RequestMapping(
        value = "/api/project",
        produces = "application/json",
        method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
public class ProjectController {

    final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }


    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project) {
        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
