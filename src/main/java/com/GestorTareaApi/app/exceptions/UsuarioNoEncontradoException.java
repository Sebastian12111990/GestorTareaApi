package com.GestorTareaApi.app.exceptions;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(Long id){
        super("usuario no encontrado con la ID : " + id);
    }
}
