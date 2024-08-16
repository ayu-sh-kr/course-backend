package dev.archimedes.coursebackend.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public record CourseFetchRecord(

        @JsonProperty("course_id")
        Integer id,
        String title,
        String code,
        String description,
        long createTime
) {

    public CourseFetchRecord(
            Integer id, String title, String code,
            String description, LocalDateTime createTime
    ) {
        this(
                id, title, code, description, createTime.toEpochSecond(ZoneOffset.of("ITC"))
        );
    }

}
