package com.pnc.backend.utils.mappers;

import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.examen.ExamenRequest;
import com.pnc.backend.dto.request.examen.ExamenUpdateRequest;
import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import com.pnc.backend.dto.response.examen.ExamenResponse;
import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.Materia;
import com.pnc.backend.entities.PreguntaOpcionMultiple;

import java.util.ArrayList;
import java.util.List;

public class ExamenMapper {
    public static Examen toEntityCreate(ExamenRequest examenRequest, Materia materia) {
        return Examen.builder()
                .name(examenRequest.getName())
                .description(examenRequest.getDescription())
                .duration(examenRequest.getDuration())
                .DateHourBegin(examenRequest.getDateHourBegin())
                .DateHourEnd(examenRequest.getDateHourEnd())
                .materia(materia)
                .isVisible(examenRequest.getIsVisible())
                .build();
    }
    public static Examen toEntityUpdate(ExamenUpdateRequest examenRequest) {
        return Examen.builder()
                .id(examenRequest.getId())
                .name(examenRequest.getName())
                .isVisible(examenRequest.getIsVisible())
                .description(examenRequest.getDescription())
                .duration(examenRequest.getDuration())
                .DateHourBegin(examenRequest.getDateHourBegin())
                .DateHourEnd(examenRequest.getDateHourEnd())
                .build();
    }

    public static ExamenResponse toDTO(Examen examen) {
        return ExamenResponse.builder()
                .id(examen.getId())
                .name(examen.getName())
                .isVisible(examen.getIsVisible())
                .description(examen.getDescription())
                .duration(examen.getDuration())
                .DateHourBegin(examen.getDateHourBegin())
                .DateHourEnd(examen.getDateHourEnd())
                .preguntaOpcionMultipleList(PreguntaOpcionMultipleMapper.toDTOList(examen.getPreguntaOpcionMultipleList()))
                .build();
    }

    public static List<ExamenResponse> toDTOList(List<Examen> examen) {
        if (examen == null) {
            return new ArrayList<>();
        }
        return examen.stream()
                .map(ExamenMapper::toDTO)
                .toList();
    }
}