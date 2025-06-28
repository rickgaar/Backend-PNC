package com.pnc.backend.service;

import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.request.usuario.UsuarioUpdateRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.entities.Usuario;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponse> findAll();
    UsuarioResponse findById(Long id);
    UsuarioResponse save(UsuarioRequest usuario);
    UsuarioResponse update(UsuarioUpdateRequest usuario);
    void delete(Long id);
    UsuarioResponse updateAvatar(String email, String newAvatar);
    UsuarioResponse findByUsernameOrEmail(String usernameorEmail);
    UsuarioResponse findByUsername(String name);
}
