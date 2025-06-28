package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleRequest;
import com.pnc.backend.dto.request.RespuestaOpcionMultiple.RespuestaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.RespuestaOpcionMultiple.RespuestaOpcionMultipleResponse;
import com.pnc.backend.entities.PreguntaOpcionMultiple;
import com.pnc.backend.entities.RespuestaOpcionMultiple;
import com.pnc.backend.exceptions.PreguntaNotFoundException;
import com.pnc.backend.exceptions.RespuestaNotFoundException;
import com.pnc.backend.repository.PreguntaOpcionMultipleRepository;
import com.pnc.backend.repository.RespuestaOpcionMultipleRepository;
import com.pnc.backend.service.RespuestaOpcionMultipleService;
import com.pnc.backend.utils.mappers.RespuestaOpcionMultipleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RespuestaOpcionMultipleServiceImpl implements RespuestaOpcionMultipleService {

    private final RespuestaOpcionMultipleRepository respuestaOpcionMultipleRepository;
    private final PreguntaOpcionMultipleRepository preguntaOpcionMultipleRepository;

    @Autowired
    public RespuestaOpcionMultipleServiceImpl(RespuestaOpcionMultipleRepository respuestaOpcionMultipleRepository, PreguntaOpcionMultipleRepository preguntaOpcionMultipleRepository) {
        this.respuestaOpcionMultipleRepository = respuestaOpcionMultipleRepository;
        this.preguntaOpcionMultipleRepository = preguntaOpcionMultipleRepository;
    }

    @Override
    public RespuestaOpcionMultipleResponse save(RespuestaOpcionMultipleRequest respuestaOpcionMultipleRequest) {
        Optional<PreguntaOpcionMultiple> preguntaOpcionMultiple=preguntaOpcionMultipleRepository.findById(respuestaOpcionMultipleRequest.getIdPreguntaOpcionMultiple());
        if(preguntaOpcionMultiple.isEmpty()){
            throw new PreguntaNotFoundException("Pregunta no encontrada");
        }
        return RespuestaOpcionMultipleMapper.toDTO(respuestaOpcionMultipleRepository.save(RespuestaOpcionMultipleMapper.toEntityCreate(respuestaOpcionMultipleRequest,preguntaOpcionMultiple.get())));
    }

    @Override
    public RespuestaOpcionMultipleResponse update(RespuestaOpcionMultipleUpdateRequest respuestaOpcionMultipleRequest) {
        Optional<RespuestaOpcionMultiple> respuestaOpcionMultiple=respuestaOpcionMultipleRepository.findById(respuestaOpcionMultipleRequest.getId());
        if(respuestaOpcionMultiple.isEmpty()){
            throw new RespuestaNotFoundException("Respuesta no encontrada");
        }
        RespuestaOpcionMultiple respuestaOpcionMultipleUpdated=RespuestaOpcionMultipleMapper.toEntityUpdate(respuestaOpcionMultipleRequest);
        respuestaOpcionMultipleUpdated.setPreguntaOpcionMultiple(respuestaOpcionMultiple.get().getPreguntaOpcionMultiple());
        return RespuestaOpcionMultipleMapper.toDTO(respuestaOpcionMultipleRepository.save(respuestaOpcionMultipleUpdated));
    }

    @Override
    public void delete(Long id) {
        respuestaOpcionMultipleRepository.deleteById(id);

    }
}
