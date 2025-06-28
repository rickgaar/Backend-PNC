package com.pnc.backend.utils.mappers;

import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.entities.PreguntaOpcionMultiple;
import com.pnc.backend.entities.RespuestaOpcionMultiple;

import java.util.ArrayList;
import java.util.List;

public class RespuestaOpcionMultipleMapper {
    public static RespuestaOpcionMultiple toEntityCreate(RespuestaOpcionMultipleRequest respuestaOpcionMultipleRequest, PreguntaOpcionMultiple preguntaOpcionMultiple) {
        return RespuestaOpcionMultiple.builder()
                .image(respuestaOpcionMultipleRequest.getImage())
                .description(respuestaOpcionMultipleRequest.getDescription())
                .isCorrect(respuestaOpcionMultipleRequest.getIsCorrect())
                .preguntaOpcionMultiple(preguntaOpcionMultiple)
                .build();
    }
    public static RespuestaOpcionMultiple toEntityUpdate(RespuestaOpcionMultipleUpdateRequest respuestaOpcionMultipleRequest) {
        return RespuestaOpcionMultiple.builder()
                .image(respuestaOpcionMultipleRequest.getImage())
                .description(respuestaOpcionMultipleRequest.getDescription())
                .isCorrect(respuestaOpcionMultipleRequest.getIsCorrect())
                .id(respuestaOpcionMultipleRequest.getId())
                .build();
    }

    public static RespuestaOpcionMultipleResponse toDTO(RespuestaOpcionMultiple respuestaOpcionMultiple) {
        return RespuestaOpcionMultipleResponse.builder()
                .image(respuestaOpcionMultiple.getImage())
                .description(respuestaOpcionMultiple.getDescription())
                .isCorrect(respuestaOpcionMultiple.getIsCorrect())
                .id(respuestaOpcionMultiple.getId())
                .build();
    }

    public static List<RespuestaOpcionMultipleResponse> toDTOList(List<RespuestaOpcionMultiple> respuestaOpcionMultiple) {
        if (respuestaOpcionMultiple == null) {
            return new ArrayList<>();
        }
        return respuestaOpcionMultiple.stream()
                .map(RespuestaOpcionMultipleMapper::toDTO)
                .toList();
    }

}
