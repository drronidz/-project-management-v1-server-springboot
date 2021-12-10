package com.cleverdeveloper.ppmtool.services;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 1:54 PM
*/

import com.cleverdeveloper.ppmtool.domain.ProjectTask;
import com.cleverdeveloper.ppmtool.repositories.BacklogRepository;
import com.cleverdeveloper.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectTaskService {
    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;

    public ProjectTaskService(BacklogRepository backlogRepository, ProjectTaskRepository projectTaskRepository) {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
    }

    public ProjectTask addProjectTask() {

         // TODO Project Task to be added to a specific project, project != null, Backlog exists.
         // TODO Set the Backlog to the Project Task
         // TODO We want our project sequence to be like this: IDPRO-1 IDPRO-2 ... 100
         // TODO Update The Backlog sequence
         // TODO Setting an INITIAL Priority when priority null
         // TODO Setting an INITIAL Status when status is null


    }
}
