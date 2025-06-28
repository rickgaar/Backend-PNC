package com.pnc.backend.security;

import com.pnc.backend.entities.Usuario;
import com.pnc.backend.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No existe un usuario con el identificador: " + usernameOrEmail));


        GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getName());

        return new User(
                usernameOrEmail,
                usuario.getPassword(),
                List.of(authority)
        );


    }
}
