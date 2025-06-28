package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.materia.MateriaRequest;
import com.pnc.backend.dto.request.materia.MateriaUpdateRequest;
import com.pnc.backend.dto.response.examen.ExamenResponse;
import com.pnc.backend.dto.response.materia.MateriaResponse;
import com.pnc.backend.dto.response.tema.TemaResponse;
import com.pnc.backend.entities.Materia;
import com.pnc.backend.entities.Usuario;
import com.pnc.backend.exceptions.DuplicatedFieldException;
import com.pnc.backend.exceptions.MateriaNotFoundException;
import com.pnc.backend.exceptions.UserNotFoundException;
import com.pnc.backend.repository.MateriaRepository;
import com.pnc.backend.repository.UsuarioRepository;
import com.pnc.backend.service.MateriaService;
import com.pnc.backend.utils.mapper.MateriaMapper;
import com.pnc.backend.utils.mappers.ExamenMapper;
import com.pnc.backend.utils.mappers.TemaMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MateriaServiceImpl implements MateriaService {

    private final MateriaRepository materiaRepository;
    private final UsuarioRepository usuarioRepository;

    public MateriaServiceImpl(MateriaRepository materiaRepository, UsuarioRepository usuarioRepository) {
        this.materiaRepository = materiaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public MateriaResponse save(MateriaRequest materia) {
        return MateriaMapper.toDTO(materiaRepository.save(MateriaMapper.toEntityCreate(materia)));
    }

    @Override
    public void delete(Long id) {
        materiaRepository.deleteById(id);
    }

    @Override
    public List<MateriaResponse> findAll() {
        return MateriaMapper.toDTOList(materiaRepository.findAll());
    }

    @Override
    public MateriaResponse findById(Long id) {
        return MateriaMapper.toDTO(materiaRepository.findById(id)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada")));
    }

    @Override
    @Transactional
    public MateriaResponse toggleVisibility(Long id) {
        Materia materia = materiaRepository.findById(id).orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));
        materia.setVisible(!materia.isVisible());
        return MateriaMapper.toDTO(materia);
    }

    @Override
    public List<ExamenResponse> findExams(Long id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isEmpty()) {
            throw new MateriaNotFoundException("Materia not found");
        }
        return ExamenMapper.toDTOList(materia.get().getExams());
    }

    @Override
    public List<TemaResponse> findTemas(Long id) {
        Optional<Materia> materia = materiaRepository.findById(id);
        if (materia.isEmpty()) {
            throw new MateriaNotFoundException("Materia not found");
        }
        return TemaMapper.toDTOList(materia.get().getTemas());
    }

    @Transactional
    public void addUser(Long idMateria, Long idUsuario) {
        Materia materia = materiaRepository.findById(idMateria)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        Set<Usuario> usuariosActuales = new HashSet<>(materia.getUsuarios());

        if (usuariosActuales.contains(usuario)) {
            throw new DuplicatedFieldException("El usuario ya está inscrito en esta materia");
        }

        materia.getUsuarios().add(usuario);
        materiaRepository.save(materia);
    }

    @Override
    public MateriaResponse update(MateriaUpdateRequest materia) {
        Materia materiaExistente = materiaRepository.findById(materia.getId())
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encotrada"));

        materiaExistente.setNombre(materia.getNombre());
        materiaExistente.setImagen(materia.getImagen());
        materiaExistente.setVisible(materiaExistente.isVisible());

        return MateriaMapper.toDTO(materiaRepository.save(materiaExistente));
    }

    @Override
    public void removeUser(Long idMateria, Long idUsuario) {
        Materia materia = materiaRepository.findById(idMateria)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado"));

        materia.getUsuarios().remove(usuario);
        usuario.getMaterias().remove(materia);

        materiaRepository.save(materia);
        usuarioRepository.save(usuario);
    }

    @Override
    public MateriaResponse findWithDetails(Long id) {
        Materia materia = materiaRepository.findById(id)
                .orElseThrow(() -> new MateriaNotFoundException("Materia no encontrada"));
        return MateriaMapper.toDTOWithUsuarios(materia);
    }
}
