package dev.archimedes.coursebackend.course.dto;

import jakarta.validation.constraints.NotBlank;

public record CourseCreateRecord(
        @NotBlank(message = "Course title is required")
        String title,
        @NotBlank(message = "Course code is required")
        String code,
        @NotBlank(message = "Course description is required")
        String description
) {
}
