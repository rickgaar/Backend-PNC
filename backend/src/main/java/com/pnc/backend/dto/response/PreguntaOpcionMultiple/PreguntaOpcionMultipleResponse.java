package com.pnc.backend.dto.response.PreguntaOpcionMultiple;

import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class PreguntaOpcionMultipleResponse {

    private Long id;

    private String image;

    private String statement;

    private List<RespuestaOpcionMultipleResponse> responses =new ArrayList<>();
}
