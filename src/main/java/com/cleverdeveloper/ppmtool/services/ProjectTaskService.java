package com.cleverdeveloper.ppmtool.services;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 1:54 PM
*/

import com.cleverdeveloper.ppmtool.domain.Backlog;
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

    public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
        // TODO Exceptions: Project not found

        // TODO Project Task to be added to a specific project, project != null, Backlog exists.
        Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

        // TODO Set the Backlog to the Project Task
        projectTask.setBacklog(backlog);

        // TODO We want our project sequence to be like this: IDPRO-1 IDPRO-2 ... 100
        Integer backlogSequence = backlog.getPTSequence();
        backlogSequence++;

        // TODO Update The Backlog sequence
        projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
        projectTask.setProjectIdentifier(projectIdentifier);

        // TODO Setting an INITIAL Priority when priority null
//        if (projectTask.getPriority() == 0 || projectTask.getPriority() == null) {
//            projectTask.setPriority(3);
//        }

        // TODO Setting an INITIAL Status when status is null
        if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
            projectTask.setStatus("TO_DO");
        }

        return projectTaskRepository.save(projectTask);
    }
}
