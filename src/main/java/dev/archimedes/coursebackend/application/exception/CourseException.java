package dev.archimedes.coursebackend.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CourseException extends RuntimeException{

    private String description;
    private final HttpStatus status;

    public CourseException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public CourseException(String message, String description, HttpStatus status) {
        super(message);
        this.description = description;
        this.status = status;
    }
}
