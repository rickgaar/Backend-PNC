package com.pnc.backend.controller;

import com.pnc.backend.dto.request.LoginRequest;
import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.response.JwtAuthResponse;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "https://learnsy-three.vercel.app", allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(loginRequest);
        JwtAuthResponse.builder().accessToken(token).build();
        return ResponseEntity.ok(JwtAuthResponse.builder().accessToken(token).build());
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}