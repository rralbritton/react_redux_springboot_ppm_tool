package io.agileintelligence.ppmtool.services.exceptionServices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectIdExceptionService extends RuntimeException {
    public ProjectIdExceptionService(String message) {
        super(message);
    }
}
