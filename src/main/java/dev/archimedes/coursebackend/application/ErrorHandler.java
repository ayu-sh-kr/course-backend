package dev.archimedes.coursebackend.application;

import dev.archimedes.coursebackend.application.api.APIResponse;
import dev.archimedes.coursebackend.application.exception.CourseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(CourseException.class)
    public ResponseEntity<APIResponse<?>> courseException(CourseException exception) {
        return new ResponseEntity<>(
                APIResponse.ofMessage(exception.getMessage()),
                exception.getStatus()
        );
    }
}
