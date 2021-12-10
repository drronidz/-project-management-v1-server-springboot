package com.cleverdeveloper.ppmtool.exceptions;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 9:17 PM
*/

public class ProjectNotFoundExceptionResponse {

    private String projectNotFound;

    public ProjectNotFoundExceptionResponse(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }

    public String getProjectNotFound() {
        return projectNotFound;
    }

    public void setProjectNotFound(String projectNotFound) {
        this.projectNotFound = projectNotFound;
    }
}
