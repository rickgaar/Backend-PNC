package com.pnc.backend.utils.mapper;

import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.request.usuario.UsuarioUpdateRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.dto.response.materia.MateriaResponse;
import com.pnc.backend.entities.Role;
import com.pnc.backend.entities.Usuario;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toEntityCreate(UsuarioRequest usuarioDTO, String encryptedPassword, Role rol, String avatarURL){
        return Usuario.builder()
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getCorreo())
                .username(usuarioDTO.getUsername())
                .password(encryptedPassword)
                .avatar(avatarURL)
                .rol(rol)
                .build();
    }

    public static Usuario toEntityUpdate(UsuarioUpdateRequest usuarioDTO){
        return Usuario.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .email(usuarioDTO.getCorreo())
                .avatar(usuarioDTO.getAvatar())
                .build();
    }

    public static UsuarioResponse toDTO(Usuario usuario){
        Set<MateriaResponse> materias = usuario.getMaterias() != null
                ? usuario.getMaterias().stream()
                .map(m -> MateriaResponse.builder()
                        .id(m.getId())
                        .nombre(m.getNombre())
                        .visible(m.isVisible())
                        .imagen(m.getImagen())
                        .usuarios(List.of())
                        .build())
                .collect(Collectors.toSet())
                : Set.of();

        return UsuarioResponse.builder()
                .usuarioId(usuario.getId())
                .nombre(usuario.getNombre())
                .correo(usuario.getEmail())
                .username(usuario.getUsername())
                .avatar(usuario.getAvatar())
                .rol(usuario.getRol().getName())
                .materias(materias)
                .build();
    }

    public static List<UsuarioResponse> toDTOList(List<Usuario> usuarios){
        return usuarios.stream()
                .map(UsuarioMapper::toDTO)
                .toList();
    }
}
