package com.GestorTareaApi.app.services;

import com.GestorTareaApi.app.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IUsuarioService {
    Optional<Usuario> buscarUsuario(Long id);
}
