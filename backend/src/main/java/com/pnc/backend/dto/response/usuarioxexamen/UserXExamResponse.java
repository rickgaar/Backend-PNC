package com.pnc.backend.dto.response.usuarioxexamen;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserXExamResponse {

    private LocalDateTime DateHourEnd;

    private LocalDateTime DateHourBegin;

    private Float calificacion;

    private Long examId;

    private Long userId;

    private String examen;
}
