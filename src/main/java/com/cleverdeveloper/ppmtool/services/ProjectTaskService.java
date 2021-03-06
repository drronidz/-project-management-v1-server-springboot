package com.cleverdeveloper.ppmtool.services;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 1:54 PM
*/

import com.cleverdeveloper.ppmtool.domain.Backlog;
import com.cleverdeveloper.ppmtool.domain.Project;
import com.cleverdeveloper.ppmtool.domain.ProjectTask;
import com.cleverdeveloper.ppmtool.exceptions.ProjectNotFoundException;
import com.cleverdeveloper.ppmtool.repositories.BacklogRepository;
import com.cleverdeveloper.ppmtool.repositories.ProjectRepository;
import com.cleverdeveloper.ppmtool.repositories.ProjectTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectTaskService {
    private final BacklogRepository backlogRepository;
    private final ProjectTaskRepository projectTaskRepository;
    private final ProjectRepository projectRepository;

    public ProjectTaskService (
            BacklogRepository backlogRepository,
            ProjectTaskRepository projectTaskRepository,
            ProjectRepository projectRepository)
    {
        this.backlogRepository = backlogRepository;
        this.projectTaskRepository = projectTaskRepository;
        this.projectRepository = projectRepository;
    }

    public ProjectTask addProjectTask (String projectIdentifier, ProjectTask projectTask) {
        // TODO Exceptions:
        //  { ProjectNotFound : "Project not Found" }
        try {
            // TODO Project Task to be added to a specific project, project != null, Backlog exists.
            Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);

            // TODO Set the Backlog to the Project Task
            projectTask.setBacklog(backlog);

            // TODO We want our project sequence to be like this: IDPRO-1 IDPRO-2 ... 100
            Integer backlogSequence = backlog.getPTSequence();
            backlogSequence++;
            backlog.setPTSequence(backlogSequence);

            // TODO Update The Backlog sequence
            projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
            projectTask.setProjectIdentifier(projectIdentifier);

            // TODO Setting an INITIAL Priority when priority null
            if (projectTask.getPriority() == null) {
                // TODO In the future we need projectTask.getPriority() == 0 to handle the Form ...
                projectTask.setPriority(3);
            }

            // TODO Setting an INITIAL Status when status is null
            if (projectTask.getStatus() == "" || projectTask.getStatus() == null) {
                projectTask.setStatus("TO_DO");
            }

            return projectTaskRepository.save(projectTask);
        }
        catch (Exception exception) {
            throw  new ProjectNotFoundException("Project not found!");
        }

    }

    public Iterable<ProjectTask> findBacklogById (String projectId) {

        Project project = projectRepository.findByProjectIdentifier(projectId);

        if (project == null) {
            throw new ProjectNotFoundException("Project with ID: " + projectId + " does not exist!");
        }

        return projectTaskRepository.findByProjectIdentifierOrderByPriority(projectId);
    }

    public ProjectTask findProjectTaskByProjectSequence (String backlogId,String projectSequence) {
        // Make sure we are searching on the right backlog
        Backlog backlog = backlogRepository.findByProjectIdentifier(backlogId);

        if (backlog== null) {
            throw new ProjectNotFoundException("Project with ID: " + backlogId + " does not exist");
        }

        // Make sure that our task exists
        ProjectTask projectTask = projectTaskRepository.findByProjectSequence(projectSequence);

        if (projectTask == null) {
            throw new ProjectNotFoundException("Project Task " + projectSequence + " not found");
        }

        // Make sure that the backlog/project id in the path corresponds to the right project
        if (!projectTask.getProjectIdentifier().equals(backlogId)) {
            throw new ProjectNotFoundException("Project Task " + projectSequence + " does not exist in project: " + backlogId);
        }

        return projectTask;
    }

    public void deleteProjectTaskByProjectSequence(String backlogId, String projectTaskId) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId);

//        Backlog backlog = projectTask.getBacklog();
//        List<ProjectTask> projectTasks = backlog.getProjectTasks();
//        projectTasks.remove(projectTask);
//        backlogRepository.save(backlog);

        projectTaskRepository.delete(projectTask);
    }

    // TODO Update Project Task ...
    public ProjectTask updateByProjectSequence(ProjectTask updatedTask, String backlogId, String projectTaskId) {
        ProjectTask projectTask = findProjectTaskByProjectSequence(backlogId, projectTaskId);

        projectTask = updatedTask;

        return projectTaskRepository.save(projectTask);
    }

    // TODO Find existing Project Task ...

    // TODO Replace it with updated Task ...

    // TODO Save Update ...


}
