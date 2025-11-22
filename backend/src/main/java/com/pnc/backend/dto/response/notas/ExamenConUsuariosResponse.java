package com.pnc.backend.dto.response.notas;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExamenConUsuariosResponse {
    private Long examenId;
    private String examenNombre;
    private String descripcion;
    private List<UsuarioNotaResponse> usuarios;
}

