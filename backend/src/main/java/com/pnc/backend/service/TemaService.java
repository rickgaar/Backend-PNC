package com.pnc.backend.service;

import com.pnc.backend.dto.request.tema.TemaRequest;
import com.pnc.backend.dto.request.tema.TemaUpdateRequest;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.entities.Tema;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface TemaService {
    List<TemaResponse> findAllByMateriaId(Long materiaId);
    TemaResponse findById(Long id);
    TemaResponse save(TemaRequest tema, MultipartFile archivo) throws IOException;
    byte[] getFileByTemaId(Long temaId) throws IOException;
    TemaResponse updateTema(Long temaId, TemaUpdateRequest request);
    void delete(Long id);
}
