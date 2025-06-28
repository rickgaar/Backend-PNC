package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.usuarioxexamen.UserXExamRequest;
import com.pnc.backend.dto.request.usuarioxexamen.UserXExamUpdateRequest;
import com.pnc.backend.dto.response.usuarioxexamen.UserXExamResponse;
import com.pnc.backend.entities.Examen;
import com.pnc.backend.entities.UserXExam;
import com.pnc.backend.entities.Usuario;
import com.pnc.backend.exceptions.ExamenNotFoundException;
import com.pnc.backend.exceptions.UserNotFoundException;
import com.pnc.backend.exceptions.UserXExamNotFoundException;
import com.pnc.backend.repository.ExamRepository;
import com.pnc.backend.repository.UserXExamRepository;
import com.pnc.backend.repository.UsuarioRepository;
import com.pnc.backend.service.UserXExamService;
import com.pnc.backend.utils.id.UserExamId;
import com.pnc.backend.utils.mappers.UserXExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserXExamServiceImpl implements UserXExamService {
    private final UserXExamRepository userXExamRepository;
    private final UsuarioRepository userRepository;
    private final ExamRepository examRepository;

    @Autowired
    public UserXExamServiceImpl(UserXExamRepository userXExamRepository, UsuarioRepository userRepository, ExamRepository examRepository) {
        this.userXExamRepository = userXExamRepository;
        this.userRepository = userRepository;
        this.examRepository = examRepository;
    }

    @Override
    public UserXExamResponse save(UserXExamRequest userXExamRequest) {
        Optional<Usuario> usuario=userRepository.findById(userXExamRequest.getUserId());
        Optional<Examen> examen=examRepository.findById(userXExamRequest.getExamId());
        if(examen.isEmpty()){
            throw new ExamenNotFoundException("Exam not found");
        }
        if(usuario.isEmpty()){
            throw new UserNotFoundException("User not found");
        }

        return UserXExamMapper.toDTO(userXExamRepository.save(UserXExamMapper.toEntityCreate(userXExamRequest,usuario.get(),examen.get())));
    }

    @Override
    public UserXExamResponse update(UserXExamUpdateRequest userXExamUpdateRequest) {
        Optional<UserXExam> userXExam=userXExamRepository.findById(UserExamId.builder().userId(userXExamUpdateRequest.getUserId()).examId(userXExamUpdateRequest.getExamId()).build());
        if(userXExam.isEmpty()){
            throw new UserXExamNotFoundException("El examen de este usuario no se encontro");
        }
        UserXExam userXExamUpdated = UserXExamMapper.toEntityUpdate(userXExamUpdateRequest, userXExam.get().getUsuario(),userXExam.get().getExamen());
        userXExamUpdated.setDateHourBegin(userXExam.get().getDateHourBegin());
        return UserXExamMapper.toDTO(userXExamRepository.save(userXExamUpdated));
    }

    @Override
    public List<UserXExamResponse> findByUsuarioIdAndExamenMateriaId(Long userId, Long materiaId) {
        Optional<List<UserXExam>> userXExams = userXExamRepository.findByUsuarioIdAndExamenMateriaId(userId,materiaId);
        if(userXExams.isEmpty()){
            throw new UserXExamNotFoundException("Los examenes de esta materia y este usuario no se encontraron");
        }
        return UserXExamMapper.toDTOList(userXExams.get());
    }

    @Override
    public UserXExamResponse findByUsuarioIdAndExamenMateriaIdAndExamenId(Long userId, Long materiaId, Long examId) {
        Optional<UserXExam> userXExam = userXExamRepository.findByUsuarioIdAndExamenMateriaIdAndExamenId(userId,materiaId,examId);
        if(userXExam.isEmpty()){
            throw new UserXExamNotFoundException("El examen de esta materia y de este usuario no se encontro");
        }
        return UserXExamMapper.toDTO(userXExam.get());
    }
}
