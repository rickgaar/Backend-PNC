package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.examen.ExamenRequest;
import com.pnc.backend.dto.request.examen.ExamenUpdateRequest;
import com.pnc.backend.dto.response.examen.ExamenResponse;
import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.Materia;
import com.pnc.backend.exceptions.ExamenNotFoundException;
import com.pnc.backend.exceptions.MateriaNotFoundException;
import com.pnc.backend.repository.ExamRepository;
import com.pnc.backend.repository.MateriaRepository;
import com.pnc.backend.service.ExamenService;
import com.pnc.backend.utils.mappers.ExamenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {
    private final ExamRepository examRepository;
    private final MateriaRepository materiaRepository;

    @Autowired
    public ExamenServiceImpl(ExamRepository examRepository, MateriaRepository materiaRepository) {
        this.examRepository = examRepository;
        this.materiaRepository = materiaRepository;
    }

    @Override
    public ExamenResponse save(ExamenRequest examenRequest) {
        Optional<Materia> materia = materiaRepository.findById(examenRequest.getIdMateria());
        if(materia.isEmpty()){
            throw new MateriaNotFoundException("Materia not found");
        }
        return ExamenMapper.toDTO(examRepository.save(ExamenMapper.toEntityCreate(examenRequest,materia.get())));
    }

    @Override
    public ExamenResponse update(ExamenUpdateRequest examenUpdateRequest) {
        Optional<Examen> examen=examRepository.findById(examenUpdateRequest.getId());
        if(examen.isEmpty()){
            throw new ExamenNotFoundException("exam not found");
        }
        Examen examenUpdated = ExamenMapper.toEntityUpdate(examenUpdateRequest);
        examenUpdated.setMateria(examen.get().getMateria());
        examenUpdated.setPreguntaOpcionMultipleList(examen.get().getPreguntaOpcionMultipleList());
        return ExamenMapper.toDTO(examRepository.save(examenUpdated));
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }

    @Override
    public ExamenResponse findById(Long id) {
        Optional<Examen> examen = examRepository.findById(id);
        if(examen.isEmpty()){
            throw new ExamenNotFoundException("Exam not found");
        }
        return ExamenMapper.toDTO(examen.get());
    }
}
