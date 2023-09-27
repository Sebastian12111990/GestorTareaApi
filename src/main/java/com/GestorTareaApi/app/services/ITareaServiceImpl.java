package com.GestorTareaApi.app.services;

import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.entities.UsuarioTarea;
import com.GestorTareaApi.app.repositories.TareaRepository;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import com.GestorTareaApi.app.repositories.UsuarioTareaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ITareaServiceImpl implements ITareaService {

    @Autowired
    private TareaRepository tareaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioTareaRepository usuarioTareaRepository;
    @Override
    @Transactional
    public List<Tarea> listarTareas() {
        return (List<Tarea>) tareaRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Tarea> buscarTareaId(Long id) {
        return tareaRepository.findById(id);
    }

    @Override
    @Transactional
    public Tarea guardarTarea(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    @Override
    @Transactional
    public void borrarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Override
    @Transactional
    public String asignarUsuarioTarea(Usuario usuario, Tarea tarea) {

        String mensaje = "";

        Optional<UsuarioTarea> usuarioTareaOptional = usuarioTareaRepository.findByUsuarioAndTarea(tarea.getId() , usuario.getId());

        if (!usuarioTareaOptional.isPresent()){

            UsuarioTarea usuarioTarea = usuarioTareaRepository.save(new UsuarioTarea(usuario , tarea));

            if ( usuarioTarea.getId() != null ) {

                mensaje =  "usuario " + usuario.getNombre()
                        + " " + usuario.getApellido()
                        + " " + "ha sido asignado en la tarea"
                        + " " + tarea.getTitulo();
            } else {
                mensaje =  "error al asignar usuario a tarea";
            }

        }else{
            mensaje = "usuario ya ha sido asignado a esta tarea";
        }

        return mensaje;
    }

    @Override
    @Transactional
    public String eliminarUsuarioTarea(Usuario usuario, Tarea tarea) {

        String mensaje = "";

        Optional<UsuarioTarea> usuarioTareaOptional = usuarioTareaRepository.findByUsuarioAndTarea(tarea.getId() , usuario.getId());

        if (usuarioTareaOptional.isPresent()){

            usuarioTareaRepository.delete(usuarioTareaOptional.get());

                mensaje =  "usuario eliminado de la tarea";

        }else{
            mensaje = "relacion entre usuario y tarea no existe nada que eliminar";
        }

        return mensaje;
    }

    @Override
    public Boolean buscarTareaPortitulo(String titulo) {

        List<Tarea> tareaList = tareaRepository.buscarTareaPorTitulo(titulo);

        if (tareaList.size()>0){
            return true;
        }else{
            return false;
        }

    }
}
