package com.GestorTareaApi.app.services;

import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IUsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public Optional<Usuario> buscarUsuario(Long id) {
        return usuarioRepository.findById(id);
    }
}
