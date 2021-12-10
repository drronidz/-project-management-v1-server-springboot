package com.cleverdeveloper.ppmtool.controllers;

import com.cleverdeveloper.ppmtool.domain.ProjectTask;
import com.cleverdeveloper.ppmtool.services.ProjectTaskService;
import com.cleverdeveloper.ppmtool.services.ValidationErrorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 2:21 PM
*/
@RestController
@RequestMapping("/api/backlog")
@CrossOrigin
public class BacklogController {

    final private ProjectTaskService projectTaskService;
    final private ValidationErrorService validationErrorService;

    public BacklogController(ProjectTaskService projectTaskService, ValidationErrorService validationErrorService) {
        this.projectTaskService = projectTaskService;
        this.validationErrorService = validationErrorService;
    }

    @PostMapping("/{backlog_id}")
    public ResponseEntity<?> addProjectTaskBacklog (
            @Valid @RequestBody ProjectTask projectTask,
            BindingResult result,
            @PathVariable String backlog_id
    ) {
        ResponseEntity<?> errors = validationErrorService.MapValidationService(result);

        if(errors != null) {
            return errors;
        }

        ProjectTask projectTaskOne = projectTaskService.addProjectTask(backlog_id, projectTask);

        return new ResponseEntity<ProjectTask>(projectTaskOne, HttpStatus.CREATED);
    }
}
