package com.GestorTareaApi.app.exceptions;

public class RelacionUsuarioTareNoExisteException extends RuntimeException{
    public RelacionUsuarioTareNoExisteException(Long usuarioId , Long tareaId){
        super("relacion entre usuario ID: "+ usuarioId+" y la tarea ID: " + tareaId + " no existe ");
    }

}
