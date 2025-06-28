package com.pnc.backend.dto.request.examen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pnc.backend.utils.dateValidation.DateExtendedValidation;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ExamenRequest {
    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotNull(message = "isVisible cannot be null")
    private Boolean isVisible;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotNull(message = "duration cannot be null")
    @Min(value = 1, message = "minimum duration is 1")
    private Integer duration;

    @FutureOrPresent(message = "Date hour begin cannot be in the past")
    private LocalDateTime DateHourBegin;

    @FutureOrPresent(message = "Date hour end cannot be in the past")
    private LocalDateTime DateHourEnd;

    @NotNull(message = "id cannot be null")
    private Long idMateria;

    @AssertTrue(message = "end date hour must be after start date hour",groups = DateExtendedValidation.class)
    @JsonIgnore
    private   boolean  isEndDateAfterStartDate () {
        return !DateHourEnd.isBefore(DateHourBegin);
    }
}
