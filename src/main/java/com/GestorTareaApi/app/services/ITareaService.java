package com.GestorTareaApi.app.services;


import com.GestorTareaApi.app.entities.Tarea;
import com.GestorTareaApi.app.entities.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ITareaService {

    List<Tarea> listarTareas();

    Optional<Tarea> buscarTareaId(Long id);

    Tarea guardarTarea(Tarea tarea);

    Tarea modificarTarea(Long id , Tarea tarea);

    void borrarTarea(Long id);
    /* asignacion deasignacion usuario */
    String asignarUsuarioTarea(Usuario usuario , Tarea tarea);

    String eliminarUsuarioTarea(Usuario usuario , Tarea tarea);

    Boolean buscarTareaPortitulo(String titulo);


}
