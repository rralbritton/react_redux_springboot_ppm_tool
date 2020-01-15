package io.agileintelligence.ppmtool.services.exceptionServices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameAlreadyExistsService extends RuntimeException {
    public UsernameAlreadyExistsService(String message) {
        super(message);
    }
}
