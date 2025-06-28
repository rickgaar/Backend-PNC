package com.pnc.backend.dto.request.usuarioxexamen;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UserXExamRequest {

    @NotNull(message = "examId cannot be null")
    private Long examId;

    @NotNull(message = "userId cannot be null")
    private Long userId;
}
