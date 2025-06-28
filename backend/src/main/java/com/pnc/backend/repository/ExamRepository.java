package com.pnc.backend.repository;

import com.pnc.backend.entities.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Examen,Long> {
}
