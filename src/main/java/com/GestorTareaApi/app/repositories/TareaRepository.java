package com.GestorTareaApi.app.repositories;

import com.GestorTareaApi.app.entities.Tarea;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TareaRepository extends CrudRepository<Tarea, Long> {
    @Query("select t from Tarea t where t.titulo = :titulo ")
    List<Tarea> buscarTareaPorTitulo(String titulo);

}
