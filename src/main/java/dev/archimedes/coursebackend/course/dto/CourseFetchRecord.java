package dev.archimedes.coursebackend.course.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneId;


public record CourseFetchRecord(

        @JsonProperty("course_id")
        Integer id,
        String title,
        String code,
        String description,
        @JsonProperty("created_on")
        long createTime
) {

    public CourseFetchRecord(
            Integer id, String title, String code,
            String description, LocalDateTime createTime
    ) {
        this(
                id, title, code, description, createTime.atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond()
        );
    }

}
