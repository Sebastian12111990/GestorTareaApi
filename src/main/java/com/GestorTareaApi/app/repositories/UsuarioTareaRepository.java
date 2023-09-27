package com.GestorTareaApi.app.repositories;

import com.GestorTareaApi.app.entities.Usuario;
import com.GestorTareaApi.app.entities.UsuarioTarea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioTareaRepository extends CrudRepository<UsuarioTarea , Long> {

    @Query("select ut from UsuarioTarea ut where ut.tarea.id = ?1 and ut.usuario.id = ?2")
    Optional<UsuarioTarea> findByUsuarioAndTarea(Long tareaId, Long usuarioId);



}
