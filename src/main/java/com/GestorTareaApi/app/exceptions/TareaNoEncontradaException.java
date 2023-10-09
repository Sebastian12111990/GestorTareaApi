package com.GestorTareaApi.app.exceptions;

public class TareaNoEncontradaException extends RuntimeException{
    public TareaNoEncontradaException(Long id){
        super("tarea no encontrada con ID : " + id);
    }
}
