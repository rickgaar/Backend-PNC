package com.pnc.backend.repository;

import com.pnc.backend.entities.Materia;
import com.pnc.backend.entities.RespuestaOpcionMultiple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaOpcionMultipleRepository extends JpaRepository<RespuestaOpcionMultiple, Long> {
}
