package com.pnc.backend.dto.request.materia;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MateriaUpdateRequest {
    @NotNull(message = "Se debe proporcionar un ID")
    private Long id;
    private String nombre;
    private boolean isVisible;
    private String imagen;
}
