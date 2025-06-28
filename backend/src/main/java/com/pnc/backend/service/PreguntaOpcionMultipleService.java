package com.pnc.backend.service;

import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.entities.PreguntaOpcionMultiple;

public interface PreguntaOpcionMultipleService {
    PreguntaOpcionMultipleResponse save(PreguntaOpcionMultipleRequest preguntaOpcionMultipleRequest);
    PreguntaOpcionMultipleResponse update(PreguntaOpcionMultipleUpdateRequest preguntaOpcionMultipleUpdateRequest);
    void delete(Long id);
}
