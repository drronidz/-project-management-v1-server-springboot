package com.cleverdeveloper.ppmtool.services;

import com.cleverdeveloper.ppmtool.domain.Backlog;
import com.cleverdeveloper.ppmtool.domain.Project;
import com.cleverdeveloper.ppmtool.exceptions.ProjectIdException;
import com.cleverdeveloper.ppmtool.repositories.BacklogRepository;
import com.cleverdeveloper.ppmtool.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 11/26/2021 10:38 PM
*/
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final BacklogRepository backlogRepository;

    public ProjectService(ProjectRepository projectRepository, BacklogRepository backlogRepository) {
        this.projectRepository = projectRepository;
        this.backlogRepository = backlogRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        // Logic
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

            if (project.getId() == null) {
                Backlog backlog = new Backlog();
                project.setBacklog(backlog);
                backlog.setProject(project);
                backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            }

            if (project.getId() != null) {
                project.setBacklog(backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
            }

            return projectRepository.save(project);
        }
        catch (Exception e) {
            throw new ProjectIdException("Project ID : " + project.getProjectIdentifier().toUpperCase() + " already exists!");
        }
    }

    public Project findProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID :" + projectId + " does not exist");
        }

        return project;
    }

    public Iterable<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier(String projectId) {
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null) {
            throw new ProjectIdException("Project ID (To Delete ...):" + projectId + " does not exist");
        }

        projectRepository.delete(project);
    }
}
