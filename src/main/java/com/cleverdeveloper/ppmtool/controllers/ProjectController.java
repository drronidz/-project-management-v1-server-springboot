package com.cleverdeveloper.ppmtool.controllers;

import com.cleverdeveloper.ppmtool.domain.Project;
import com.cleverdeveloper.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity <?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {

        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error: result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            // return new ResponseEntity<String>("Invalid Project Object ", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        Project project1 = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }
}
