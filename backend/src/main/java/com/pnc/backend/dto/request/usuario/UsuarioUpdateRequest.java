package com.pnc.backend.dto.request.usuario;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioUpdateRequest {
    @NotNull(message = "Debes poner el id de un usuario")
    private Long id;
    private String nombre;
    private String contrasena;
    private String correo;
    private String avatar;
}
