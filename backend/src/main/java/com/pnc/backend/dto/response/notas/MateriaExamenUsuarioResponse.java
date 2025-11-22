package com.pnc.backend.dto.response.notas;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MateriaExamenUsuarioResponse {
    private Long materiaId;
    private String materiaNombre;
    private List<ExamenConUsuariosResponse> examenes;
}
