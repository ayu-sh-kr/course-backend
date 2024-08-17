package dev.archimedes.coursebackend.application.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Builder
@Getter @Setter
public class APIResponse<T> {

    private String message;
    private String description;
    private T body;
    private long time;


    public static <T> APIResponse<T> ofMessage(String message) {
        return APIResponse.<T>builder()
                .message(message)
                .time(LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond())
                .build();
    }

    public static <T> APIResponse<T> ofDescription(String message, String description) {
        return APIResponse.<T>builder()
                .message(message)
                .description(description)
                .time(LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond())
                .build();
    }

    public static <T> APIResponse<T> ofBody(String message, T body) {
        return APIResponse.<T>builder()
                .message(message)
                .body(body)
                .time(LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond())
                .build();
    }

    public static <T> APIResponse<T> ofBodyAndDescription(String message, String description, T body) {
        return APIResponse.<T>builder()
                .message(message)
                .description(description)
                .body(body)
                .time(LocalDateTime.now().atZone(ZoneId.of("Asia/Kolkata")).toEpochSecond())
                .build();
    }
}