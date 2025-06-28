package com.pnc.backend.utils.mappers;

import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.PreguntaOpcionMultiple;
import com.pnc.backend.entities.RespuestaOpcionMultiple;

import java.util.ArrayList;
import java.util.List;

public class PreguntaOpcionMultipleMapper {
    public static PreguntaOpcionMultiple toEntityCreate(PreguntaOpcionMultipleRequest preguntaOpcionMultipleRequest, Examen examen) {
        return PreguntaOpcionMultiple.builder()
                .image(preguntaOpcionMultipleRequest.getImage())
                .statement(preguntaOpcionMultipleRequest.getStatement())
                .examen(examen)
                .build();
    }
    public static PreguntaOpcionMultiple toEntityUpdate(PreguntaOpcionMultipleUpdateRequest preguntaOpcionMultipleRequest) {
        return PreguntaOpcionMultiple.builder()
                .image(preguntaOpcionMultipleRequest.getImage())
                .statement(preguntaOpcionMultipleRequest.getStatement())
                .id(preguntaOpcionMultipleRequest.getId())
                .build();
    }

    public static PreguntaOpcionMultipleResponse toDTO(PreguntaOpcionMultiple preguntaOpcionMultiple) {
        return PreguntaOpcionMultipleResponse.builder()
                .image(preguntaOpcionMultiple.getImage())
                .statement(preguntaOpcionMultiple.getStatement())
                .id(preguntaOpcionMultiple.getId())
                .responses(RespuestaOpcionMultipleMapper.toDTOList(preguntaOpcionMultiple.getRespuestaOpcionMultipleList()))
                .build();
    }

    public static List<PreguntaOpcionMultipleResponse> toDTOList(List<PreguntaOpcionMultiple> preguntaOpcionMultiple) {
        if (preguntaOpcionMultiple == null) {
            return new ArrayList<>();
        }
        return preguntaOpcionMultiple.stream()
                .map(PreguntaOpcionMultipleMapper::toDTO)
                .toList();
    }
}
