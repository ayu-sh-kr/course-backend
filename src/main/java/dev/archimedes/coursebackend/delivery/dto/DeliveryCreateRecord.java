package dev.archimedes.coursebackend.delivery.dto;

import jakarta.validation.constraints.Pattern;

public record DeliveryCreateRecord(


        @Pattern(regexp = "^(FIRST | SECOND | THIRD | FOURTH | FIFTH | SIXTH | SEVENTH | EIGHTH)")
        String year,

        @Pattern(regexp = "^(I|II|III|IV|V|VI|VII|VIII)", message = "Semester must be a Roman numeral between I and VII")
        String semester,
        Integer courseId
) {
}
