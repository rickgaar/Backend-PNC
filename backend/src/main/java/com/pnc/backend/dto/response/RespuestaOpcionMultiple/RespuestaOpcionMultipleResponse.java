package com.pnc.backend.dto.response.RespuestaOpcionMultiple;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RespuestaOpcionMultipleResponse {

    private Long id;

    private String image;

    private String description;

    private Boolean isCorrect;

}
