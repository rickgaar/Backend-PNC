package com.pnc.backend.utils.mapper;

import com.pnc.backend.dto.request.materia.MateriaRequest;
import com.pnc.backend.dto.request.materia.MateriaUpdateRequest;
import com.pnc.backend.dto.response.materia.MateriaResponse;
import com.pnc.backend.dto.response.usuario.UsuarioSimplificadoResponse;
import com.pnc.backend.entities.Materia;

import java.util.List;

public class MateriaMapper {
    public static Materia toEntityCreate(MateriaRequest materiaDTO){
        return Materia.builder()
                .nombre(materiaDTO.getNombre())
                .visible(materiaDTO.isVisible())
                .imagen(materiaDTO.getImagen())
                .build();
    }

    public static Materia toEntityUpdate(MateriaUpdateRequest materiaDTO){
        return Materia.builder()
                .id(materiaDTO.getId())
                .nombre(materiaDTO.getNombre())
                .visible(materiaDTO.isVisible())
                .imagen(materiaDTO.getImagen())
                .build();
    }

    public static MateriaResponse toDTO(Materia materia){
        return MateriaResponse.builder()
                .id(materia.getId())
                .nombre(materia.getNombre())
                .visible(materia.isVisible())
                .imagen(materia.getImagen())
                .build();
    }

    public static List<MateriaResponse> toDTOList(List<Materia> materias) {
        return materias.stream()
                .map(MateriaMapper::toDTO)
                .toList();
    }

    public static MateriaResponse toDTOWithUsuarios(Materia materia){
        List<UsuarioSimplificadoResponse> usuarios = materia.getUsuarios().stream()
                .map(u -> new UsuarioSimplificadoResponse(u.getId(), u.getNombre(), u.getUsername(), u.getEmail(), u.getAvatar()))
                .toList();
        return new MateriaResponse(
                materia.getId(), materia.getNombre(), materia.isVisible(), materia.getImagen(), usuarios);
    }
}
