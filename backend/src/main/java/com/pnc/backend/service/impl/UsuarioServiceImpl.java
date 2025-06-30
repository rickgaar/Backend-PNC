package com.pnc.backend.service.impl;

import com.pnc.backend.dto.request.usuario.UsuarioRequest;
import com.pnc.backend.dto.request.usuario.UsuarioUpdateRequest;
import com.pnc.backend.dto.response.usuario.UsuarioResponse;
import com.pnc.backend.entities.Role;
import com.pnc.backend.entities.Usuario;
import com.pnc.backend.exceptions.DuplicatedFieldException;
import com.pnc.backend.exceptions.UserNotFoundException;
import com.pnc.backend.repository.RoleRepository;
import com.pnc.backend.repository.UsuarioRepository;
import com.pnc.backend.service.UsuarioService;
import com.pnc.backend.utils.mapper.UsuarioMapper;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UsuarioResponse> findAll() {
        return UsuarioMapper.toDTOList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponse findById(Long id) {
        return UsuarioMapper.toDTO(usuarioRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario no encontrado")));
    }

    @Override
    @Transactional
    public UsuarioResponse save(UsuarioRequest usuarioDTO) {

        if (usuarioRepository.existsByUsernameIgnoreCase(usuarioDTO.getUsername())){
            throw new DuplicatedFieldException("Ese username ya esta registrado");
        }
        if (usuarioRepository.existsByEmailIgnoreCase(usuarioDTO.getCorreo())){
            throw new DuplicatedFieldException("Ese correo ya esta registrado");
        }

        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.getContrasena());

        Role rolUsuario = roleRepository.findByName("usuario")
                .orElseThrow(() -> new RuntimeException("Rol 'usuario' no encontrado"));

        String avatarPorDefecto = "https://static.vecteezy.com/system/resources/previews/009/292/244/non_2x/default-avatar-icon-of-social-media-user-vector.jpg";

        Usuario usuario = UsuarioMapper.toEntityCreate(usuarioDTO, passwordEncriptada, rolUsuario, avatarPorDefecto);

        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }


    @Override
    public UsuarioResponse update(UsuarioUpdateRequest usuario) {
        return UsuarioMapper.toDTO(usuarioRepository.save(UsuarioMapper.toEntityUpdate(usuario)));
    }

    @Override
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public UsuarioResponse updateAvatar(String usernameOrEmail, String nuevoAvatar) {
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con username o email: " + usernameOrEmail));

        usuario.setAvatar(nuevoAvatar);
        usuarioRepository.save(usuario);

        return UsuarioMapper.toDTO(usuario);
    }


    @Override
    public UsuarioResponse findByUsernameOrEmail(String usernameOrEmail) {
        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con username o email: " + usernameOrEmail));
        return UsuarioMapper.toDTO(usuario);
    }

    @Override
    public UsuarioResponse findByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con username: " + username));
        return UsuarioMapper.toDTO(usuario);
    }


}
