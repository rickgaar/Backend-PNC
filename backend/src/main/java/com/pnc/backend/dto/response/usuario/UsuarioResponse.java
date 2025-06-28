package com.pnc.backend.dto.response.usuario;

import com.pnc.backend.dto.response.materia.MateriaResponse;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UsuarioResponse {
    private Long usuarioId;
    private String nombre;
    private String username;
    private String correo;
    private String avatar;
    private String rol;
    private Set<MateriaResponse> materias;
}
