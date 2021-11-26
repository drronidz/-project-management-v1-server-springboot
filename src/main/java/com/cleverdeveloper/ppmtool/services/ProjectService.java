package com.cleverdeveloper.ppmtool.services;

import com.cleverdeveloper.ppmtool.domain.Project;
import com.cleverdeveloper.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project saveOrUpdateProject(Project project) {

        // Logic

        return projectRepository.save(project);
    }
}
