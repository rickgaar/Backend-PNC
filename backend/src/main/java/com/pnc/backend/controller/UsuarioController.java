package com.pnc.backend.controller;

import com.pnc.backend.dto.response.GeneralResponse;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.security.JwtTokenProvider;
import com.pnc.backend.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> whoami(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String usernameOrEmail = jwtTokenProvider.getUsernameFromToken(token);
        UsuarioResponse response = usuarioService.findByUsernameOrEmail(usernameOrEmail);
        if (response.getAvatar() != null && !response.getAvatar().startsWith("http")) {
            response.setAvatar("https://backend-pnc-production-d8ff.up.railway.app/api/user/avatar/" + response.getAvatar());
        }
        return buildResponse("Datos obtenidos exitosamente", HttpStatus.OK, response);
    }

    @PatchMapping("/changeAvatar")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> updateAvatar(
            @RequestHeader("Authorization") String authHeader,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        String token = authHeader.replace("Bearer ", "");
        String email = jwtTokenProvider.getUsernameFromToken(token);

        UsuarioResponse response = usuarioService.updateAvatarFile(email, file);

        if (response.getAvatar() != null && !response.getAvatar().startsWith("http")) {
            response.setAvatar("https://backend-pnc-production-d8ff.up.railway.app/api/user/avatar/" + response.getAvatar());
        }

        return buildResponse("Avatar actualizado exitosamente", HttpStatus.OK, response);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<GeneralResponse> delete(@PathVariable Long id, @RequestHeader("Authorization") String authHeader) {
        UsuarioResponse usuario = usuarioService.findById(id);
        usuarioService.delete(id);
        return buildResponse("Usuario eliminado exitosamente", HttpStatus.OK, usuario);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAnyRole('admin','usuario')")
    public ResponseEntity<GeneralResponse> findByUsername(@PathVariable String username, @RequestHeader("Authorization") String authHeader) {
        UsuarioResponse usuario = usuarioService.findByUsername(username);
        return buildResponse("Usuarios encontrados", HttpStatus.OK, usuario);
    }

    @GetMapping("/avatar/{filename}")
    public ResponseEntity<?> getAvatar(@PathVariable String filename) {
        try {
            Path imagePath = Paths.get("uploads/avatars").resolve(filename).normalize();

            Resource resource = new UrlResource(imagePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            // Detecta automáticamente el tipo MIME (image/png, image/jpg, etc)
            String contentType = Files.probeContentType(imagePath);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al cargar avatar");
        }
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
