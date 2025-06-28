package com.pnc.backend.repository;

import com.pnc.backend.entities.Tema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Long> {
    List<Tema> findByMateriaId(Long materiaId);
}
