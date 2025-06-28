package com.pnc.backend.service;

import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;

public interface RespuestaOpcionMultipleService {
    RespuestaOpcionMultipleResponse save(RespuestaOpcionMultipleRequest respuestaOpcionMultipleRequest);
    RespuestaOpcionMultipleResponse update(RespuestaOpcionMultipleUpdateRequest respuestaOpcionMultipleRequest);
    void delete(Long id);
}
