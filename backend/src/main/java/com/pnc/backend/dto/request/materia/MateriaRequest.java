package com.pnc.backend.dto.request.materia;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MateriaRequest {

    @NotNull(message = "Name cannot be null")
    private String nombre;

    @NotNull(message = "Visibility cannot be null")
    private boolean visible;

    @NotBlank(message = "You must enter a valid image")
    private String imagen;
}
