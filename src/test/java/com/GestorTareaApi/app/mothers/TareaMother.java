package com.GestorTareaApi.app.mothers;

import com.GestorTareaApi.app.entities.EstadoTarea;
import com.GestorTareaApi.app.entities.Tarea;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TareaMother {
    public static Tarea tareaNull(){
        return new Tarea();
    }
    public static Tarea tareaPredeterminada(){
        return  new Tarea( 1L , "Tarea de Ejemplo", new Timestamp(System.currentTimeMillis()), new EstadoTarea("PENDIENTE"));
    }
    public static Tarea tareaModificada(){
        return  new Tarea( 1L , "Tarea Modificada", new Timestamp(System.currentTimeMillis()), new EstadoTarea("COMPLETADA"));
    }
    public static List<Tarea> tareaListaPredeterminada(){
        return Arrays.asList(
                tareaPredeterminada(),
                new Tarea(2L, "Otra Tarea", new Timestamp(System.currentTimeMillis()), new EstadoTarea("COMPLETADA"))
        );
    }

}
