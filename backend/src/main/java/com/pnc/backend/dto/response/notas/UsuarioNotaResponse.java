package com.pnc.backend.dto.response.notas;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioNotaResponse {
    private Long usuarioId;
    private String nombre;
    private Float calificacion;
}
