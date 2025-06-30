package com.pnc.backend.repository;

import com.pnc.backend.entities.UserXExam;
import com.pnc.backend.utils.id.UserExamId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserXExamRepository extends JpaRepository<UserXExam, UserExamId> {
    Optional<List<UserXExam>> findByUsuarioIdAndExamenMateriaId(Long userId, Long materiaId);
    Optional<UserXExam> findByUsuarioIdAndExamenMateriaIdAndExamenId(Long userId, Long materiaId, Long examId);
    Optional<List<UserXExam>> findByExamenId(Long examenId);
    Optional<UserXExam> findByExamenIdAndUsuarioId(Long examenId, Long usuarioId);
}
