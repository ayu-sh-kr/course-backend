package dev.archimedes.coursebackend.delivery.dto;

import jakarta.validation.constraints.Pattern;

public record DeliveryCreateRecord(

        @Pattern(regexp = "\\d{4}", message = "Year must be a four-digit number")
        String year,

        @Pattern(regexp = "^(I|II|III|IV|V|VI|VII|VIII)", message = "Semester must be a Roman numeral between I and VII")
        String semester,
        Integer courseId
) {
}
