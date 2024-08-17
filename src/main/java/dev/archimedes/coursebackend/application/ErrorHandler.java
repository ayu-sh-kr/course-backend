package dev.archimedes.coursebackend.application;

import dev.archimedes.coursebackend.application.api.APIResponse;
import dev.archimedes.coursebackend.application.exception.CourseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(CourseException.class)
    public ResponseEntity<APIResponse<?>> courseException(CourseException exception) {
        return new ResponseEntity<>(
                APIResponse.ofMessage(exception.getMessage()),
                exception.getStatus()
        );
    }

    @ResponseBody
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public String handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException exception) {
        System.out.println(exception.getMessage());
        return "acceptable MIME type:" + MediaType.APPLICATION_JSON_VALUE;
    }
}
