package com.pnc.backend.dto.request.tema;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TemaUpdateRequest {
    @NotNull(message = "Name cannot be null")
    private String nombre;

    @NotNull(message = "La visibilidad es obligatoria")
    private Boolean visibilidad;
}
