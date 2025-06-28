package com.pnc.backend.dto.request.PreguntaOpcionMultiple;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PreguntaOpcionMultipleUpdateRequest {
    private String image;

    @NotBlank(message = "statement cannot be blank")
    private String statement;

    @NotNull(message = "id cannot be null")
    private Long id;
}
