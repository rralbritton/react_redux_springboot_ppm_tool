package io.agileintelligence.ppmtool.services.exceptionServices;

import io.agileintelligence.ppmtool.models.exceptionModels.BacklogIdException;
import io.agileintelligence.ppmtool.models.exceptionModels.ProjectIdException;
import io.agileintelligence.ppmtool.models.exceptionModels.UsernameAlreadyExistsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdExceptionService ex, WebRequest req){
        ProjectIdException exceptionResponse = new ProjectIdException(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleBacklogIdException(BacklogIdExceptionService ex, WebRequest req){
        BacklogIdException backlogExceptionResponse = new BacklogIdException(ex.getMessage());
        return new ResponseEntity<>(backlogExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsService ex, WebRequest req){
        UsernameAlreadyExistsResponse exceptionResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
