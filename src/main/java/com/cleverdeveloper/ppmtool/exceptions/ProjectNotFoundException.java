package com.cleverdeveloper.ppmtool.exceptions;

/*
PROJECT NAME : ppmtool
Module NAME: IntelliJ IDEA
Author Name : @ DRRONIDZ
DATE : 12/10/2021 9:18 PM
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundException extends RuntimeException{
    
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
