package com.pnc.backend.service;

import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamUpdateRequest;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.dto.response.usuarioxexamen.UserXExamResponse;
import com.pnc.backend.entities.UserXExam;

import java.util.List;

public interface UserXExamService {
    UserXExamResponse save(UserXExamRequest userXExamRequest);
    UserXExamResponse update(UserXExamUpdateRequest userXExamUpdateRequest);
    List<UserXExamResponse> findByUsuarioIdAndExamenMateriaId(Long userId, Long materiaId);
    UserXExamResponse findByUsuarioIdAndExamenMateriaIdAndExamenId(Long userId, Long materiaId, Long examId);
}
