package com.pnc.backend.dto.request.RespuestaOpcionMultiple;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaOpcionMultipleUpdateRequest {

    @NotNull(message = "id cannot be null")
    private Long id;

    private String image;

    private String description;

    @NotNull(message = "correct cannot be null")
    private Boolean isCorrect;

}
