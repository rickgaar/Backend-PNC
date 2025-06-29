package com.pnc.backend.controller;

import com.pnc.backend.dto.response.GeneralResponse;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.security.JwtTokenProvider;
import com.pnc.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://learnsy-three.vercel.app", allowCredentials = "true")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/whoami")
    public ResponseEntity<GeneralResponse> whoami(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        return buildResponse("Datos obtenidos exitosamente", HttpStatus.OK, response);
    }

    @PatchMapping("/changeAvatar")
    public ResponseEntity<GeneralResponse> updateAvatar(@RequestHeader("Authorization") String authHeader, @RequestParam("avatar") String nuevoAvatar) {

        String token = authHeader.replace("Bearer ", "");

        String email = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.updateAvatar(email, nuevoAvatar);
        return buildResponse("Avatar cambiado exitosamente", HttpStatus.OK, response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GeneralResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        UsuarioResponse usuario = usuarioService.findById(id);
        usuarioService.delete(id);
        return buildResponse("Usuario eliminado exitosamente", HttpStatus.OK, usuario);
    }

    @GetMapping("/{username}")
    public ResponseEntity<GeneralResponse> findByUsername(@PathVariable String username, @RequestHeader("Authorization") String authHeader) {
        UsuarioResponse usuario = usuarioService.findByUsername(username);
        return buildResponse("Usuarios encontrados", HttpStatus.OK, usuario);
    }

    public ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(GeneralResponse.builder()
                .message(message)
                .status(status.value())
                .data(data)
                .uri(uri)
                .time(LocalDate.now())
                .build());
    }
}
