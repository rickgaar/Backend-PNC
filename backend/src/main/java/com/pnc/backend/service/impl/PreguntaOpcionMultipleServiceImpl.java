package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleRequest;
import com.pnc.backend.dto.request.PreguntaOpcionMultiple.PreguntaOpcionMultipleUpdateRequest;
import com.pnc.backend.dto.response.PreguntaOpcionMultiple.PreguntaOpcionMultipleResponse;
import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.PreguntaOpcionMultiple;
import com.pnc.backend.exceptions.ExamenNotFoundException;
import com.pnc.backend.exceptions.PreguntaNotFoundException;
import com.pnc.backend.repository.ExamRepository;
import com.pnc.backend.repository.PreguntaOpcionMultipleRepository;
import com.pnc.backend.service.PreguntaOpcionMultipleService;
import com.pnc.backend.utils.mappers.PreguntaOpcionMultipleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreguntaOpcionMultipleServiceImpl implements PreguntaOpcionMultipleService {

    private final PreguntaOpcionMultipleRepository preguntaOpcionMultipleRepository;
    private final ExamRepository examRepository;

    @Autowired
    public PreguntaOpcionMultipleServiceImpl(PreguntaOpcionMultipleRepository preguntaOpcionMultipleRepository, ExamRepository examRepository) {
        this.preguntaOpcionMultipleRepository = preguntaOpcionMultipleRepository;
        this.examRepository = examRepository;
    }

    @Override
    public PreguntaOpcionMultipleResponse save(PreguntaOpcionMultipleRequest preguntaOpcionMultipleRequest) {
        Optional<Examen> examen=examRepository.findById(preguntaOpcionMultipleRequest.getIdExam());
        if(examen.isEmpty()){
            throw new ExamenNotFoundException("Exam not found");
        }
        return PreguntaOpcionMultipleMapper.toDTO(preguntaOpcionMultipleRepository.save(PreguntaOpcionMultipleMapper.toEntityCreate(preguntaOpcionMultipleRequest,examen.get())));
    }

    @Override
    public PreguntaOpcionMultipleResponse update(PreguntaOpcionMultipleUpdateRequest preguntaOpcionMultipleUpdateRequest) {
        Optional<PreguntaOpcionMultiple> preguntaOpcionMultiple =preguntaOpcionMultipleRepository.findById(preguntaOpcionMultipleUpdateRequest.getId());
        if(preguntaOpcionMultiple.isEmpty()){
            throw new PreguntaNotFoundException("Question not found");
        }
        PreguntaOpcionMultiple preguntaOpcionMultipleUpdated= PreguntaOpcionMultipleMapper.toEntityUpdate(preguntaOpcionMultipleUpdateRequest);
        preguntaOpcionMultipleUpdated.setRespuestaOpcionMultipleList(preguntaOpcionMultiple.get().getRespuestaOpcionMultipleList());
        preguntaOpcionMultipleUpdated.setExamen(preguntaOpcionMultiple.get().getExamen());
        return PreguntaOpcionMultipleMapper.toDTO(preguntaOpcionMultipleRepository.save(preguntaOpcionMultipleUpdated));
    }

    @Override
    public void delete(Long id) {
        preguntaOpcionMultipleRepository.deleteById(id);

    }
}

