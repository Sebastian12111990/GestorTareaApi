package com.GestorTareaApi.app.mothers;

import com.GestorTareaApi.app.entities.EstadoTarea;
import com.GestorTareaApi.app.entities.Tarea;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TareaMother {
    public static Tarea tareaPredeterminada(){
        return  new Tarea( 1L , "Tarea de Ejemplo", new Timestamp(System.currentTimeMillis()), new EstadoTarea("PENDIENTE"));
    }

    public static Tarea tareaNull(){
        return new Tarea();
    }

}
