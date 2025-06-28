package com.pnc.backend.dto.request.RespuestaOpcionMultiple;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaOpcionMultipleRequest {

    private String image;

    private String description;

    @NotNull(message = "correct cannot be null")
    private Boolean isCorrect;

    @NotNull(message = "id of question cannot be null")
    private Long idPreguntaOpcionMultiple;
}
