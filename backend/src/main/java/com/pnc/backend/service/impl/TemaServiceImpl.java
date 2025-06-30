package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.tema.TemaRequest;
import com.pnc.backend.dto.request.tema.TemaUpdateRequest;
import com.pnc.backend.dto.response.tema.FileResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.entities.Materia;
import com.pnc.backend.entities.Tema;
import com.pnc.backend.exceptions.FileNotFoundException;
import com.pnc.backend.exceptions.FileNotSupportedException;
import com.pnc.backend.exceptions.MateriaNotFoundException;
import com.pnc.backend.exceptions.TemaNotFoundException;
import com.pnc.backend.repository.MateriaRepository;
import com.pnc.backend.repository.TemaRepository;
import com.pnc.backend.service.TemaService;
import com.pnc.backend.utils.mappers.TemaMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TemaServiceImpl implements TemaService {
    private final TemaRepository temaRepository;
    private final MateriaRepository materiaRepository;

    @Value("${app.upload.dir:uploads}")
    private String uploadDir;

    public TemaServiceImpl(TemaRepository temaRepository, MateriaRepository materiaRepository) {
        this.temaRepository = temaRepository;
        this.materiaRepository = materiaRepository;
    }

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo crear el directorio de uploads", e);
        }
    }

    @Override
    public List<TemaResponse> findAllByMateriaId(Long materiaId) {
        return TemaMapper.toDTOList(temaRepository.findByMateriaId(materiaId));
    }

    @Override
    public TemaResponse findById(Long id) {
        return TemaMapper.toDTO(temaRepository.findById(id)
                .orElseThrow(() -> new MateriaNotFoundException("Tema no encontrado")));
    }

    @Override
    public TemaResponse save(TemaRequest tema, MultipartFile archivo) throws IOException {
        Optional<Materia> materia = materiaRepository.findById(tema.getIdMateria());

        if(materia.isEmpty()){
            throw new MateriaNotFoundException("Materia not found");
        }

        assert archivo != null;
        if (!Objects.equals(archivo.getContentType(), "application/pdf")) {
            throw new FileNotSupportedException("Solo se admiten archivos tipo pdf");
        }

        String nombreArchivo = archivo.getOriginalFilename();
        Path rutaArchivo = Paths.get(uploadDir, nombreArchivo);

        //Guardando el archivo
        Files.copy(archivo.getInputStream(), rutaArchivo, StandardCopyOption.REPLACE_EXISTING);

        Tema temaData = temaRepository.save(
                TemaMapper
                        .toEntityCreate(
                                tema,
                                materia.get(),
                                FileResponse.builder().contenido(nombreArchivo).filePath(String.valueOf(rutaArchivo)).build())
        );

        return TemaMapper.toDTO(temaData);
    }

    @Override
    public byte[] getFileByTemaId(Long temaId) throws IOException {
        Optional<Tema> tema = temaRepository.findById(temaId);
        if (tema.isEmpty()) {
            throw new TemaNotFoundException("Tema no encontrado");
        }

        Path rutaArchivo = Paths.get(tema.get().getFilePath());

        byte[] file = Files.readAllBytes(rutaArchivo);
        if (file.length == 0){
            throw new FileNotFoundException("El archivo no existe");
        }
        return file;
    }

    @Override
    public TemaResponse updateTema(Long temaId, TemaUpdateRequest request) {
        Tema tema = temaRepository.findById(temaId)
                .orElseThrow(() -> new MateriaNotFoundException("Tema no encotrada"));

        tema.setNombre(request.getNombre());
        tema.setVisibilidad(request.getVisibilidad());

        return TemaMapper.toDTO(temaRepository.save(tema));
    }

    @Override
    public void delete(Long id) {
        Optional<Tema> tema = temaRepository.findById(id);
        if (tema.isEmpty()) {
            throw new TemaNotFoundException("Tema no encontrado");
        }

        eliminarArchivo(tema.get().getFilePath());

        temaRepository.deleteById(id);
    }

    private void eliminarArchivo(String rutaArchivo) {
        try {
            if (rutaArchivo != null) {
                Files.deleteIfExists(Paths.get(rutaArchivo));
            }
        } catch (IOException e) {
            System.err.println("Error al eliminar archivo: " + e);
        }
    }
}
