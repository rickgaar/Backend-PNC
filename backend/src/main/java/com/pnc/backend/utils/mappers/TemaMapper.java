package com.pnc.backend.utils.mappers;

import com.pnc.backend.dto.request.tema.TemaRequest;
import com.pnc.backend.dto.request.tema.TemaUpdateRequest;
import com.pnc.backend.dto.response.tema.FileResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.entities.Materia;
import com.pnc.backend.entities.Tema;

import java.util.List;
import java.util.Optional;

public class TemaMapper {
    public static Tema toEntityCreate(TemaRequest temaDTO, Materia materia, FileResponse fileRes){
        return Tema.builder()
                .nombre(temaDTO.getNombre())
                .visibilidad(temaDTO.getVisibilidad())
                .nombreArchivo(fileRes.getContenido())
                .filePath(fileRes.getFilePath())
                .materia(materia)
                .build();
    }

    public static Tema toEntityUpdate(TemaUpdateRequest temaDTO, FileResponse fileRes){
        return Tema.builder()
                .nombre(temaDTO.getNombre())
                .visibilidad(temaDTO.getVisibilidad())
                .filePath(fileRes.getFilePath())
                .build();
    }

    public static TemaResponse toDTO(Tema tema){
        return TemaResponse.builder()
                .id(tema.getTemaId())
                .nombre(tema.getNombre())
                .visibilidad(tema.getVisibilidad())
                .nombreArchivo(tema.getNombreArchivo())
                .filePath(tema.getFilePath())
                .build();
    }

    public static List<TemaResponse> toDTOList(List<Tema> temas) {
        return temas.stream()
                .map(TemaMapper::toDTO)
                .toList();
    }
}
