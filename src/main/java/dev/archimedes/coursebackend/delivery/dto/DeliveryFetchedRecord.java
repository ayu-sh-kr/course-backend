package dev.archimedes.coursebackend.delivery.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public record DeliveryFetchedRecord(
        Integer id,
        String year,
        String semester,

        @JsonProperty("course_id")
        Integer courseId,
        @JsonProperty("create_time")
        long createTime
) {

    public DeliveryFetchedRecord(Integer id, String year, String semester, Integer courseId, LocalDateTime time) {
        this(id, year, semester, courseId, time.toEpochSecond(ZoneOffset.of("IST")));
    }
}
