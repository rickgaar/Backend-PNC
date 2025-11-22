package com.pnc.backend.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioRequest {
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El correo no puede ir nulo")
    @Email(message = "El correo debe ser valido con @")
    private String correo;

    @NotNull(message = "El username no puede ser nulo")
    private String username;

    @NotNull(message = "La contrasena no puede ser nula")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,16}$",
            message = "La contraseña debe tener entre 8 y 16 caracteres, incluir mayúsculas, minúsculas y números."
    )
    private String contrasena;
}
