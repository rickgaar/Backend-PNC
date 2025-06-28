package com.pnc.backend.dto.response.tema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TemaResponse {
    private Long id;
    private String nombre;
    private String nombreArchivo;
    private String filePath;
    private Boolean visibilidad;
}
