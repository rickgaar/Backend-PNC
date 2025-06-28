package com.pnc.backend.service;

import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.request.examen.ExamenRequest;
import com.pnc.backend.dto.request.examen.ExamenUpdateRequest;
import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import com.pnc.backend.dto.response.examen.ExamenResponse;

public interface ExamenService {
    ExamenResponse save(ExamenRequest examenRequest);
    ExamenResponse update(ExamenUpdateRequest examenUpdateRequest);
    void delete(Long id);
    ExamenResponse findById(Long id);
}
