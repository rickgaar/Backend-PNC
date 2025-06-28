package com.pnc.backend.dto.request.tema;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class TemaRequest {
    @NotNull(message = "Name cannot be null")
    private String nombre;

    @NotNull(message = "Content cannot be null")
    private String contenido;

    @NotNull(message = "La visibilidad es obligatoria")
    private Boolean visibilidad;

    @NotNull(message = "id cannot be null")
    private Long idMateria;
}
