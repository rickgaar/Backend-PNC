package com.pnc.backend.dto.response.materia;

import com.pnc.backend.dto.response.usuario.UsuarioSimplificadoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MateriaResponse {
    private Long id;
    private String nombre;
    private boolean visible;
    private String imagen;
    private List<UsuarioSimplificadoResponse> usuarios = List.of();
}
