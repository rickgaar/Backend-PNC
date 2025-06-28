package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.LoginRequest;
import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.security.JwtTokenProvider;
import com.pnc.backend.service.AuthService;
import com.pnc.backend.service.UsuarioService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    AuthenticationManager authenticationManager;
    JwtTokenProvider jwtTokenProvider;
    private final UsuarioService usuarioService;

    @Override
    public String login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getIdentifier(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    @Transactional
    public UsuarioResponse register(UsuarioRequest request) {
        return usuarioService.save(request);
    }

}
