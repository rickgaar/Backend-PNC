package com.pnc.backend.utils.mappers;

import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamUpdateRequest;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.dto.response.usuarioxexamen.UserXExamResponse;
import com.pnc.backend.entities.*;
import com.pnc.backend.utils.id.UserExamId;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserXExamMapper {
    public static UserXExam toEntityCreate(UserXExamRequest userXExamRequest, Usuario usuario, Examen examen) {
        return UserXExam.builder()
                .id(UserExamId.builder().userId(userXExamRequest.getUserId()).examId(userXExamRequest.getExamId()).build())
                .usuario(usuario)
                .examen(examen)
                .DateHourBegin(LocalDateTime.now())
                .DateHourEnd(null)
                .calificacion(null)
                .build();
    }
    public static UserXExam toEntityUpdate(UserXExamUpdateRequest userXExamRequest, Usuario usuario, Examen examen) {
        return UserXExam.builder()
                .id(UserExamId.builder().userId(userXExamRequest.getUserId()).examId(userXExamRequest.getExamId()).build())
                .usuario(usuario)
                .examen(examen)
                .DateHourEnd(LocalDateTime.now())
                .calificacion(userXExamRequest.getCalificacion())
                .build();
    }

    public static UserXExamResponse toDTO(UserXExam userXExam) {
        return UserXExamResponse.builder()
                .userId(userXExam.getId().getUserId())
                .examId(userXExam.getId().getExamId())
                .DateHourBegin(userXExam.getDateHourBegin())
                .DateHourEnd(userXExam.getDateHourEnd())
                .calificacion(userXExam.getCalificacion())
                .examen(userXExam.getExamen().getName())
                .build();
    }

    public static List<UserXExamResponse> toDTOList(List<UserXExam> userXExamResponses) {
        if(userXExamResponses.isEmpty()){
            return new ArrayList<>();
        }
        return userXExamResponses.stream()
                .map(UserXExamMapper::toDTO)
                .toList();
    }


}
