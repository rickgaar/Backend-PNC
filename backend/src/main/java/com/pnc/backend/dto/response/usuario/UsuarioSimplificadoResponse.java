package com.pnc.backend.dto.response.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioSimplificadoResponse {
    private Long id;
    private String nombre;
    private String username;
    private String email;
    private String avatar;
}
