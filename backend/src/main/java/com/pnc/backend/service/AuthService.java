package com.pnc.backend.service;

import com.pnc.backend.dto.request.LoginRequest;
import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;

public interface AuthService {
    String login(LoginRequest loginRequest);
    UsuarioResponse register(UsuarioRequest request);
}
