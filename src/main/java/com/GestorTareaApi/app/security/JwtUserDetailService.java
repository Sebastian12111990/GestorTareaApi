package com.GestorTareaApi.app.security;

import java.util.stream.Collectors;

import com.GestorTareaApi.app.entities.UsuarioPerfil;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return this.usuarioRepository.findByEmail(username).map(usuario -> {

            final var authorities = usuario.getUsuarioPerfiles().stream()
                    .map(usuarioPerfil  -> new SimpleGrantedAuthority(usuarioPerfil.getPerfil().getNombrePerfil()))
                    .toList();

            return new User(usuario.getEmail() , usuario.getPassword(), authorities);

        }).orElseThrow(()-> new UsernameNotFoundException("User no existe"));

        
    }

}
