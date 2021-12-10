package com.cleverdeveloper.ppmtool.repositories;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 1:56 PM
*/

import com.cleverdeveloper.ppmtool.domain.ProjectTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long> {

    List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);

    ProjectTask findByProjectSequence(String projectSequence);
}
