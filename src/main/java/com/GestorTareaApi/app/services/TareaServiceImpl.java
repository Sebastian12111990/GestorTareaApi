package com.GestorTareaApi.app.services;

import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.entities.UsuarioTarea;
import com.GestorTareaApi.app.exceptions.RelacionUsuarioTareNoExisteException;
import com.GestorTareaApi.app.exceptions.TareaNoEncontradaException;
import com.GestorTareaApi.app.repositories.TareaRepository;
import com.GestorTareaApi.app.repositories.UsuarioRepository;
import com.GestorTareaApi.app.repositories.UsuarioTareaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaServiceImpl implements ITareaService {
    private final TareaRepository tareaRepository;
    private final UsuarioRepository usuarioRepository;
    private final UsuarioTareaRepository usuarioTareaRepository;

    public TareaServiceImpl(TareaRepository tareaRepository, UsuarioRepository usuarioRepository, UsuarioTareaRepository usuarioTareaRepository) {
        this.tareaRepository = tareaRepository;
        this.usuarioRepository = usuarioRepository;
        this.usuarioTareaRepository = usuarioTareaRepository;
    }
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
    public Tarea guardarTarea(Tarea tarea)   {
        try{
            return (tareaRepository.save(tarea));
        }catch(ConstraintViolationException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Tarea modificarTarea(Long id, Tarea tarea) {

        return this.buscarTareaId(id).map(tareaExistente -> {

            tareaExistente.setEstado(tarea.getEstado());
            tareaExistente.setTitulo(tarea.getTitulo());
            tareaExistente.setFechaDevencimiento(tarea.getFechaDevencimiento());

            return tareaRepository.save(tareaExistente);

        }).orElseThrow(() -> new TareaNoEncontradaException(id));


    }

    @Override
    @Transactional
    public void borrarTarea(Long id) {
        tareaRepository.deleteById(id);
    }

    @Transactional //Programacion Funcional
    public String asignarUsuarioTarea(Usuario usuario, Tarea tarea) {
        return usuarioTareaRepository.findByUsuarioAndTarea(tarea.getId() , usuario.getId()).map( usuarioTarea -> {
            return "usuario ya ha sido asignado a esta tarea" ;
        }).orElseGet(() -> {
            usuarioTareaRepository.save(new UsuarioTarea(usuario , tarea)).getId();
            return "usuario ha sido asignado en la tarea";
        });

    }

    @Override
    @Transactional
    public String eliminarUsuarioTarea(Usuario usuario, Tarea tarea) {
         return  usuarioTareaRepository.findByUsuarioAndTarea(tarea.getId() , usuario.getId()).map(usuarioTarea -> {
             usuarioTareaRepository.delete(usuarioTarea);
             return "usuario ha sido eliminado de la tarea";
         }).orElseThrow(() -> new RelacionUsuarioTareNoExisteException(usuario.getId(),tarea.getId()));

    }
    @Override
    public Boolean buscarTareaPortitulo(String titulo) {
        return tareaRepository.countBuscarTareaPorTitulo(titulo)>0;
    }
}
