package com.GestorTareaApi.app;

import com.GestorTareaApi.app.dtos.ResponseCustom;
import com.GestorTareaApi.app.exceptions.RelacionUsuarioTareNoExisteException;
import com.GestorTareaApi.app.exceptions.TareaNoEncontradaException;
import com.GestorTareaApi.app.exceptions.UsuarioNoEncontradoException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleFeignUnauthorizedException(DataIntegrityViolationException e) {
        return new ResponseCustom("error", "No existe relación", HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleRuntimeExceptionException(RuntimeException e) {
        e.printStackTrace();
        return new ResponseCustom("error", e.getMessage(), HttpStatus.BAD_REQUEST.value());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return new ResponseCustom("error ", "Formato de Propiedad invalido -> " + e.getMessage(),  HttpStatus.BAD_REQUEST.value());
    }

    @ExceptionHandler(TareaNoEncontradaException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseCustom handleTareaNoEncontradaException(TareaNoEncontradaException e) {
        return new ResponseCustom("info", e.getMessage() ,   HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseCustom handleUsuarioNoEncontradoException(UsuarioNoEncontradoException e){
        return new ResponseCustom("info", e.getMessage() , HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(RelacionUsuarioTareNoExisteException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleRelacionUsuarioTareNoExisteException(RelacionUsuarioTareNoExisteException e ){
        return new ResponseCustom("info" , e.getMessage() , HttpStatus.NOT_FOUND.value());
    }


    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleValidationException(ValidationException e ){
        return new ResponseCustom("info" , e.getMessage() , HttpStatus.NOT_FOUND.value());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseCustom handleConstraintViolationException(ConstraintViolationException e ){
        return new ResponseCustom("info" , e.getMessage() , HttpStatus.NOT_FOUND.value());
    }





}