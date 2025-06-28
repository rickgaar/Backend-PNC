package com.pnc.backend.dto.request.usuarioxexamen;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserXExamUpdateRequest {

    @NotNull(message = "calificacion cannot be null")
    @Min(value = 0,message = "Min value of calificacion is 0")
    private Float calificacion;

    @NotNull(message = "examId cannot be null")
    private Long examId;

    @NotNull(message = "userId cannot be null")
    private Long userId;
}
