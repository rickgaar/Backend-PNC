package com.pnc.backend.dto.response.examen;

import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ExamenResponse {
    private Long id;

    private String name;

    private boolean isVisible;

    private String description;

    private Integer duration;

    private LocalDateTime DateHourBegin;

    private LocalDateTime DateHourEnd;

    private List<PreguntaOpcionMultipleResponse> preguntaOpcionMultipleList=new ArrayList<>();

}
