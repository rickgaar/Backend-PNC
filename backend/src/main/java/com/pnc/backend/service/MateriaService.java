package com.pnc.backend.service;

import com.pnc.backend.dto.request.materia.MateriaRequest;
import com.pnc.backend.dto.request.materia.MateriaUpdateRequest;
import com.pnc.backend.dto.response.examen.ExamenResponse;
import com.pnc.backend.dto.response.materia.MateriaResponse;
import com.pnc.backend.dto.response.notas.MateriaExamenUsuarioResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;

import java.util.List;

public interface MateriaService {
    MateriaResponse save(MateriaRequest materia);
    void delete(Long id);
    List<MateriaResponse> findAll();
    MateriaResponse findById(Long id);
    MateriaResponse toggleVisibility(Long id);
    List<ExamenResponse> findExams(Long id);

    List<TemaResponse> findTemas(Long id);

    void addUser(Long idMateria, Long idUsuario);
    MateriaResponse update(MateriaUpdateRequest materia);
    void removeUser(Long idMateria, Long idUsuario);
    MateriaResponse findWithDetails(Long id);
    MateriaExamenUsuarioResponse getExamenesConUsuarios(Long materiaId);

}
